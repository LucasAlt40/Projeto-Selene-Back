package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.order.ItemOrder;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.infra.utils.DateHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Order findById(int orderId) {
        String orderQuery = "SELECT id, customer_id, total_price, status, created_at FROM tb_header_order WHERE id = ?";

        return jdbc.queryForObject(orderQuery, (rs, rowNum) -> mapOrder(rs) , orderId);
    }

    @Override
    public Order save(Order order) {

        Long orderId = order.getId();

        if (orderId != 0) {
            // TODO fazer update
        }

        createOrderHeader(order);
        createOrderItems(order);

        return order;
    }

    private void createOrderHeader(Order order) {
        jdbc.update(
                "INSERT INTO tb_header_order (id, customer_id, total_price, status, created_at)" + "values (?,?,?,?,?)",
                order.getId(),
                order.getCustomer(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    private void createOrderItems(Order order) {
        var itemOrders = order.getItems();
        itemOrders.forEach(itemOrder -> {
            jdbc.update(
                    "INSERT INTO tb_item_order(order_id, item_id, ticket_category_id, ticket_category_price, ticket_category_description, ticket_category_quantity) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    order.getId(),
                    itemOrder.getId(),
                    itemOrder.getTicketCategoryId(),
                    itemOrder.getTicketCategoryPrice(),
                    itemOrder.getTicketCategoryDescription(),
                    itemOrder.getTicketCategoryQuantity()
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
