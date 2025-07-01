package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.TicketOrder;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;
import br.selene.projectseleneback.infra.utils.DateHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcOrderRepository implements IOrderRepository {

    private final JdbcTemplate jdbc;
    private final ICustomerRepository customerRepository;
    private final ITicketCategoryRepository ticketCategoryRepository;
    private final IEventRepository eventRepository;

    public JdbcOrderRepository(JdbcTemplate jdbc, ICustomerRepository customerRepository, ITicketCategoryRepository ticketCategoryRepository, IEventRepository eventRepository) {
        this.jdbc = jdbc;
        this.customerRepository = customerRepository;
        this.ticketCategoryRepository = ticketCategoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Order findById(int orderId) {

        String orderQuery = "SELECT id, customer_id, total_price, status, created_at FROM tb_header_order WHERE id = ?";
        String itemsQuery = "SELECT order_id, ticket_category_id, ticket_category_price, ticket_category_description, ticket_category_quantity, event_id FROM tb_item_order WHERE order_id = ?";

        List<TicketOrder> tickets = jdbc.query(itemsQuery, this::mapTicketOrder, orderId);

        Order order = jdbc.queryForObject(orderQuery, (rs, rowNum) -> mapOrder(rs, tickets) , orderId);

        return null;
    }

    @Override
    public Boolean save(Order order) {
        return null;
    }

    @Override
    public List<TicketOrder> findItemsByOrderId(int orderId) {
        return List.of();
    }

    private Order mapOrder(ResultSet rs, List<TicketOrder> tickets) throws SQLException {
        int customerId = rs.getInt("customer_id");
        String status = rs.getString("status");
        LocalDateTime createdAt = DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at"));

        Customer customer = customerRepository.findById(customerId);
        OrderStatusEnum orderStatus = OrderStatusEnum.valueOf(status);

        return new Order(
                rs.getInt("id"),
                customer,
                orderStatus,
                createdAt,
                tickets
        );
    }


    private TicketOrder mapTicketOrder(ResultSet rs, int rowNum) throws SQLException {
        int ticketCategoryId = rs.getInt("ticket");

        int oldTicketPrice = rs.getInt("ticket_category_price");
        String oldTicketDescription = rs.getString("ticket_category_description");
        int oldTicketCategoryQuantity = rs.getInt("ticket_category_quantity");
        int eventId = rs.getInt("event_id");

        Event event = eventRepository.findById(eventId);

        TicketCategory ticketCategory = ticketCategoryRepository.findById(ticketCategoryId);
        ticketCategory.setPrice(oldTicketPrice);
        ticketCategory.setDescription(oldTicketDescription);
        ticketCategory.setQuantity(oldTicketCategoryQuantity);


        return new TicketOrder(
            rs.getInt("order_id"),
            ticketCategory,
            LocalDateTime.now(),
            event
        );
    }
}
