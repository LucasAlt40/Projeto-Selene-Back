package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.infra.utils.DateHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
    public Boolean save(Order order) {
        return null;
    }

    private Order mapOrder(ResultSet rs) throws SQLException {
        String status = rs.getString("status");
        LocalDateTime createdAt = DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at"));

        OrderStatusEnum orderStatus = OrderStatusEnum.valueOf(status);

        return new Order(
                rs.getInt("id"),
                rs.getInt("customer_id"),
                orderStatus,
                createdAt
        );
    }



}
