package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.CheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.repository.ICheckoutRepository;
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
import java.util.ArrayList;
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
                    CheckoutStatusEnum.valueOf(rs.getString("status")),
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
        String checkoutId = checkout.getId();

        if (checkoutId == null || checkoutId.isBlank()) {
            createCheckout(checkout);
            return checkout;
        }

        updateCheckout(checkout);
        return checkout;
    }


    private void createCheckout(Checkout checkout) {
        jdbc.update(
                "INSERT INTO tb_checkout_order (id_order, payment_link, status, payment_status) " +
                        "VALUES (?, ?, ?, ?)",
                checkout.getOrder().getId(),
                checkout.getPaymentLink(),
                checkout.getStatus().name(),
                checkout.getPaymentStatus().name()
        );
    }

    private void updateCheckout(Checkout checkout) {
        StringBuilder sql = new StringBuilder("UPDATE tb_checkout_order SET ");
        List<Object> params = new ArrayList<>();
        List<String> fields = new ArrayList<>();

        if (checkout.getOrder() != null && checkout.getOrder().getId() != 0) {
            fields.add("id_order = ?");
            params.add(checkout.getOrder().getId());
        }

        if (checkout.getPaymentLink() != null && !checkout.getPaymentLink().isBlank()) {
            fields.add("payment_link = ?");
            params.add(checkout.getPaymentLink());
        }

        if (checkout.getStatus() != null) {
            fields.add("status = ?");
            params.add(checkout.getStatus().name());
        }

        if (checkout.getPaymentStatus() != null) {
            fields.add("payment_status = ?");
            params.add(checkout.getPaymentStatus().name());
        }

        sql.append(String.join(", ", fields));
        sql.append(" WHERE id = ?");
        params.add(checkout.getId());

        jdbc.update(sql.toString(), params.toArray());
    }

}
