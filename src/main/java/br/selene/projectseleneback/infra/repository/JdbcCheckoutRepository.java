package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.CheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.repository.ICheckoutRepository;
import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.TicketOrder;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCheckoutRepository implements ICheckoutRepository {

    private final JdbcTemplate jdbc;
    private final IOrderRepository orderRepository;

    public JdbcCheckoutRepository(JdbcTemplate jdbc, IOrderRepository orderRepository) {
        this.jdbc = jdbc;
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Checkout> findAll(Pageable pageable) {
        String rowCountSql = "SELECT count(*) FROM tb_checkout_order";
        int total = jdbc.queryForObject(rowCountSql, Integer.class);

        String querySql =
                "SELECT " +
                        "c.id AS checkout_id, c.id_order, c.payment_link, c.status AS checkout_status, c.payment_status, " +
                        "o.id AS order_id, o.status AS order_status, o.created_at AS order_created_at " +
                        "FROM tb_checkout_order c " +
                        "INNER JOIN tb_header_order o ON c.id_order = o.id " +
                        "LIMIT ? OFFSET ?";

        List<Checkout> checkouts = jdbc.query(querySql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("order_id"));
            order.setStatus(OrderStatusEnum.valueOf(rs.getString("order_status")));
            order.setCreatedAt(rs.getTimestamp("order_created_at").toLocalDateTime());

            return new Checkout(
                    rs.getString("checkout_id"),
                    order,
                    rs.getString("payment_link"),
                    CheckoutStatusEnum.valueOf(rs.getString("checkout_status")),
                    PaymentCheckoutStatusEnum.valueOf(rs.getString("payment_status"))
            );
        }, pageable.getPageSize(), pageable.getOffset());

        for (Checkout c : checkouts) {
            List<TicketOrder> items = orderRepository.findItemsByOrderId(c.getOrder().getId());
            c.getOrder().setItems(items);
        }

        return new PageImpl<>(checkouts, pageable, total);
    }


    @Override
    public Checkout findById(int checkoutId) {
        return null;
    }

    @Override
    public Checkout save(Checkout checkout) {
        return null;
    }

    private Checkout mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setStatus(OrderStatusEnum.valueOf(rs.getString("order_status")));
        order.setCreatedAt(rs.getTimestamp("order_created_at").toLocalDateTime());

        return new Checkout(
                rs.getString("checkout_id"),
                order,
                rs.getString("payment_link"),
                CheckoutStatusEnum.valueOf(rs.getString("checkout_status")),
                PaymentCheckoutStatusEnum.valueOf(rs.getString("payment_status"))
        );
    }

}
