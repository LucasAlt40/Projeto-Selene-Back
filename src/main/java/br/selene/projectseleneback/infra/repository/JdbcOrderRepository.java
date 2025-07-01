package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.TicketOrder;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderRepository implements IOrderRepository {

    private final JdbcTemplate jdbc;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Order findById(int orderId) {
        return jdbc.queryForObject("SELECT id, customer_id, total_price, status, created_at FROM tb_header_order", Order.class);
    }

    @Override
    public Boolean save(Order order) {
        return null;
    }

    @Override
    public List<TicketOrder> findItemsByOrderId(int orderId) {
        return List.of();
    }
}
