package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.ticket.Ticket;
import br.selene.projectseleneback.domain.ticket.repository.ITicketRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTicketRepository implements ITicketRepository {

    private final JdbcTemplate jdbc;

    public JdbcTicketRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Ticket findById(Long ticketId) {
        String sql = """
                SELECT id, status, customer_id, customer_document, customer_name,
                       event_id, event_title, event_description, event_date,
                       ticket_category_id, ticket_category_price, ticket_category_description,
                       created_at
                  FROM tb_ticket
                 WHERE id = ?
                """;
        return jdbc.queryForObject(sql, this::mapRow, ticketId);
    }

    @Override
    public Ticket save(Ticket ticket) {
        createTicket(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllByEvent(int eventId) {
        String sql = """
                SELECT id, status, customer_id, customer_document, customer_name,
                       event_id, event_title, event_description, event_date,
                       ticket_category_id, ticket_category_price, ticket_category_description,
                       created_at
                  FROM tb_ticket
                 WHERE event_id = ?
                """;
        return jdbc.query(sql, this::mapRow, eventId);
    }

    @Override
    public List<Ticket> findAllByCustomer(int customerId) {
        String sql = """
                SELECT id, status, customer_id, customer_document, customer_name,
                       event_id, event_title, event_description, event_date,
                       ticket_category_id, ticket_category_price, ticket_category_description,
                       created_at
                  FROM tb_ticket
                 WHERE customer_id = ?
                """;
        return jdbc.query(sql, this::mapRow, customerId);
    }

    @Override
    public Ticket invalidateTicket(Long ticketId) {
        String sql = "UPDATE tb_ticket SET status = ? WHERE id = ?";
        jdbc.update(sql, "INVALIDATED", ticketId);
        return findById(ticketId);
    }

    private void createTicket(Ticket ticket) {
        String sql = """
                INSERT INTO tb_ticket (status, customer_id, customer_document, customer_name,
                                      event_id, event_title, event_description, event_date,
                                      ticket_category_id, ticket_category_price, ticket_category_description,
                                      created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbc.update(sql,
                ticket.getStatus(),
                ticket.getCustomerId(),
                ticket.getCustomerDocument(),
                ticket.getCustomerName(),
                ticket.getEventId(),
                ticket.getEventTitle(),
                ticket.getEventDescription(),
                ticket.getEventDate(),
                ticket.getTicketCategoryId(),
                ticket.getTicketCategoryPrice(),
                ticket.getTicketCategoryDescription(),
                ticket.getCreatedAt()
        );
    }


    private Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        ticket.setStatus(rs.getString("status"));
        ticket.setCustomerId(rs.getLong("customer_id"));
        ticket.setCustomerDocument(rs.getString("customer_document"));
        ticket.setCustomerName(rs.getString("customer_name"));
        ticket.setEventId(rs.getLong("event_id"));
        ticket.setEventTitle(rs.getString("event_title"));
        ticket.setEventDescription(rs.getString("event_description"));
        ticket.setEventDate(rs.getTimestamp("event_date").toLocalDateTime());
        ticket.setTicketCategoryId(rs.getLong("ticket_category_id"));
        ticket.setTicketCategoryPrice(rs.getLong("ticket_category_price"));
        ticket.setTicketCategoryDescription(rs.getString("ticket_category_description"));
        ticket.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return ticket;
    }
}
