package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventRepository implements IEventRepository {

    private final JdbcTemplate jdbc;

    public JdbcEventRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        String rowCountSql = "SELECT count(*) AS row_count " +
                "FROM tb_event";

        int total = jdbc.queryForObject(rowCountSql, Integer.class);


        String querySql = "SELECT id, title, description, date, status, tickets, createdAt " +
                "FROM tb_event " +
                "LIMIT " + pageable.getPageSize() + " " +
                "OFFSET " + pageable.getOffset();

        List<Event> events = jdbc.query(querySql, this::mapRow);

        return new PageImpl<Event>(events, pageable, total);
    }

    @Override
    public Iterable<Event> findAll() {
        return null;
    }

    @Override
    public Event findById(int customerId) {
        return jdbc.queryForObject("SELECT id, title, description, date, status, tickets, createdAt  FROM tb_customer where id = ?", this::mapRow, customerId);
    }

    @Override
    public Event save(Event event) {
        int eventId = event.getId();

        if(eventId != 0) {
            updateEvent(event);
            return event;
        }

        createEvent(event);
        return event;
    }


    private Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Event(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                LocalDateTime.parse(rs.getString("date")),
                rs.getString("status"),
                rs.getString("tickets"),
                LocalDateTime.parse(rs.getString("createdAt"))
        );
    }

    private void createEvent(Event event) {
        jdbc.update(
                "insert into tb_customer(title, description, date, tickets) "
                        + "values (?, ?, ?, ?)",
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getTickets()
        );
    }

    private void updateEvent(Event event) {
        StringBuilder updateQuery = new StringBuilder("UPDATE tb_event SET ");
        List<String> fieldsToUpdate = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (event.getTitle() != null && !event.getTitle().isBlank()) {
            fieldsToUpdate.add("name = ?");
            params.add(event.getTitle());
        }
        if (event.getDescription()) != null && !event.getDescription().isBlank()) {
            fieldsToUpdate.add("description = ?");
            params.add(event.getDescription());
        }
        if (event.getDate() != null && !event.getDate().isBlank()) {
            fieldsToUpdate.add("date = ?");
            params.add(event.getDate());
        }
        if (event.getTickets() != null && !event.getTickets().isBlank()) {
            fieldsToUpdate.add("tickets = ?");
            params.add(event.getTickets());
        }

        updateQuery.append(String.join(", ", fieldsToUpdate));
        updateQuery.append(" WHERE id = ?");
        params.add(event.getId());

        jdbc.update(
                updateQuery.toString(),
                params.toArray()
        );
    }

}
