package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.CheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.repository.ICheckoutRepository;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.infra.exception.CheckoutOperationException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                "SELECT id AS checkout_id, id_order, payment_link, status, payment_status " +
                        "FROM tb_checkout_order " +
                        "LIMIT ? OFFSET ?";

        List<Checkout> checkouts = jdbc.query(querySql, (rs, rowNum) -> {
            int orderId = rs.getInt("id_order");

            Order order = orderRepository.findById(orderId);

            return new Checkout(
                    rs.getString("checkout_id"),
                    order.getId(),
                    rs.getString("payment_link"),
                    CheckoutStatusEnum.valueOf(rs.getString("status")),
                    PaymentCheckoutStatusEnum.valueOf(rs.getString("payment_status"))
            );
        }, pageable.getPageSize(), pageable.getOffset());

        return new PageImpl<>(checkouts, pageable, total);
    }


    @Override
    public Checkout findById(String checkoutId) {
        String sql = "SELECT id, id_order, payment_link, status, payment_status " +
                "FROM tb_checkout_order WHERE id = ?";

       try {
           return jdbc.queryForObject(sql, (rs, rowNum) -> {
               int orderId = rs.getInt("id_order");
               Order order = this.orderRepository.findById(orderId);
               String status = rs.getString("status");
               CheckoutStatusEnum statusEnum = status != null ? CheckoutStatusEnum.valueOf(status) : null;

               return new Checkout(
                       rs.getString("id"),
                       order.getId(),
                       rs.getString("payment_link"),
                       statusEnum,
                       PaymentCheckoutStatusEnum.valueOf(rs.getString("payment_status"))
               );
           }, checkoutId);
       } catch (DataAccessException ex) {
           return  null;
       }
    }


    @Override
    public Checkout save(Checkout checkout) {
        String checkoutId = checkout.getId();
        var existedCheckout = findById(checkoutId);

        if (existedCheckout == null) {
            createCheckout(checkout);
            return checkout;
        }

        return checkout;
    }

    @Override
    public List<Checkout> findCheckoutsByOrderId(long orderId) {
        String sql = "SELECT id AS checkout_id, payment_link, status, payment_status " +
                "FROM tb_checkout_order WHERE id_order = ?";

        Order order = orderRepository.findById(orderId);

        return jdbc.query(sql, (rs, rowNum) -> new Checkout(
                rs.getString("checkout_id"),
                order.getId(),
                rs.getString("payment_link"),
                CheckoutStatusEnum.valueOf(rs.getString("status")),
                PaymentCheckoutStatusEnum.valueOf(rs.getString("payment_status"))
        ), orderId);
    }

    @Override
    public Checkout deleteCheckoutById(String checkoutId) {
        String sql = "UPDATE tb_checkout_order SET status= ? WHERE id = ?";

       int rows = jdbc.update(sql,CheckoutStatusEnum.EXPIRED.name(), checkoutId);

       if (rows > 0) {
           this.updateCheckoutPaymentStatus(checkoutId, PaymentCheckoutStatusEnum.CANCELED);
           return findById(checkoutId);
       }
       throw new CheckoutOperationException("Não foi possível cancelar o checkout");
    }

    @Override
    public Checkout deleteCheckoutByOrderId(long orderId) {
        String sql = "UPDATE tb_checkout_order SET status=? WHERE id_order = ?";

        int rows = jdbc.update(sql,CheckoutStatusEnum.EXPIRED.name(), orderId);

        if (rows > 0) {
            this.updateCheckoutPaymentStatusByOrderId(orderId, PaymentCheckoutStatusEnum.CANCELED);
            return findCheckoutsByOrderId(orderId).getFirst();
        }
        throw new CheckoutOperationException("Não foi possível cancelar o checkout");
    }

    @Override
    public Checkout updateCheckoutPaymentStatusByOrderId(long orderId, PaymentCheckoutStatusEnum checkoutStatus) {
        String sql = "UPDATE tb_checkout_order SET payment_status= ? " + "WHERE id_order = ?";

        int rows = jdbc.update(sql, checkoutStatus.name(), orderId);

        if (rows > 0) {
            return findCheckoutsByOrderId(orderId).getFirst();
        }
        throw new CheckoutOperationException("Não foi possível cancelar o checkout");
    }

    @Override
    public Checkout updateCheckoutPaymentStatus(String checkoutId, PaymentCheckoutStatusEnum checkoutStatus) {
        String sql = "UPDATE tb_checkout_order" +
                "\tSET payment_status= ? WHERE id = ?";

        int rows = jdbc.update(sql, checkoutStatus.name(), checkoutId);

        if (rows > 0) {
            return findById(checkoutId);
        }
        throw new CheckoutOperationException("Não foi possível cancelar o checkout");
    }


    private void createCheckout(Checkout checkout) {
        jdbc.update(
                "INSERT INTO tb_checkout_order (id, id_order, payment_link, status, payment_status) " +
                        "VALUES (?, ?, ?, ?, ?)",
                checkout.getId(),
                checkout.getOrder(),
                checkout.getPaymentLink(),
                checkout.getStatus().name(),
                checkout.getPaymentStatus().name()
        );
    }

    private void updateCheckout(Checkout checkout) {
        StringBuilder sql = new StringBuilder("UPDATE tb_checkout_order SET ");
        List<Object> params = new ArrayList<>();
        List<String> fields = new ArrayList<>();

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
