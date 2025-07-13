package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.order.ItemOrder;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.infra.exception.OrderOperationException;
import br.selene.projectseleneback.infra.utils.DateHelper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcOrderRepository implements IOrderRepository {

    private final JdbcTemplate jdbc;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Order findById(long orderId) {
        String orderQuery = "SELECT id, customer_id, total_price, status, created_at FROM tb_header_order WHERE id = ?";
        return jdbc.queryForObject(orderQuery, (rs, rowNum) -> mapOrder(rs) , orderId);
    }

    @Override
    public Order save(Order order) {
        KeyHolder keyHolder = createOrderHeader(order);

        Number generatedId = (Number) keyHolder.getKeys().get("id");
        if (generatedId != null) {
            order.setId(generatedId.longValue());
        }

        createOrderItems(order.getItems(), order.getId());

        return order;
    }

    @Override
    public List<Order> findAllByEvent(int eventId) {
        return List.of();
    }

    @Override
    public List<Order> findAllByCustomer(int customerId) {
        return List.of();
    }

    @Override
    public Order deleteById(long orderId) {
       String sql =
                "UPDATE tb_header_order\n" +
                "\tSET status= \n" +
                OrderStatusEnum.CANCELLED +
                "\tWHERE id = ?";

      int rows = jdbc.update(sql, orderId);

      if (rows > 0) {
          return findById(orderId);
      }

      throw new OrderOperationException("Não foi possível cancelar seu pedido!");
    }


    private KeyHolder createOrderHeader(Order order) {
        final String sql = "INSERT INTO tb_header_order (customer_id, total_price, status) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getCustomerId());
            ps.setLong(2, order.getTotalPrice());
            ps.setString(3,
                    order.getStatus() != null ? order.getStatus().name() : OrderStatusEnum.WAITING_PAYMENT.name()
            );
            return ps;
        }, keyHolder);

        return keyHolder;
    }


    private void createOrderItems(List<ItemOrder> items, Long orderId) {
        items.forEach(i -> {
            jdbc.update(
                    "INSERT INTO tb_item_order (order_id, ticket_category_id, ticket_category_price, ticket_category_description, ticket_category_quantity, event_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    orderId,
                    i.getTicketCategoryId(),
                    i.getTicketCategoryPrice(),
                    i.getTicketCategoryDescription(),
                    i.getTicketCategoryQuantity(),
                    i.getEventId()
            );
        });
    }

    private Order mapOrder(ResultSet rs) throws SQLException {
        String status = rs.getString("status");
        LocalDateTime createdAt = DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at"));

        OrderStatusEnum orderStatus = OrderStatusEnum.valueOf(status);

        return new Order(
                rs.getLong("id"),
                rs.getLong("customer_id"),
                orderStatus,
                new ArrayList<ItemOrder>(), // TO-DO recuperar informações com left join no banco
                createdAt
        );
    }



}
