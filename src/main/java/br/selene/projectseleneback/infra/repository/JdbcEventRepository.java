package br.selene.projectseleneback.infra.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.TicketCategory;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import br.selene.projectseleneback.infra.utils.DateHelper;

@Repository
public class JdbcEventRepository implements IEventRepository {

	private final JdbcTemplate jdbc;

	public JdbcEventRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Page<Event> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event findById(int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TicketCategory addTicketCategory(TicketCategory ticketCategory) {
		String sql = """
					INSERT INTO tb_ticket_category(price, description, quantity, quantity_available, event_id)
					VALUES (?, ?, ?, ?, ?)
				""";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, ticketCategory.getPrice());
			ps.setString(2, ticketCategory.getDescription());
			ps.setInt(3, ticketCategory.getQuantity());
			ps.setInt(4, ticketCategory.getQuantityAvaliable());
			ps.setLong(5, ticketCategory.getEventId());
			return ps;
		}, keyHolder);

		Number generatedId = (Number) keyHolder.getKeys().get("id");
		if (generatedId != null) {
			ticketCategory.setId(generatedId.longValue());
		}

		return ticketCategory;
	}

	@Override
	public TicketCategory updateTicketCategory(TicketCategory ticketCategory) {
		StringBuilder updateQuery = new StringBuilder("UPDATE tb_ticket_category SET ");
		List<String> fieldsToUpdate = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		if (ticketCategory.getPrice() != 0) {
			fieldsToUpdate.add("price = ?");
			params.add(ticketCategory.getPrice());
		}
		if (ticketCategory.getDescription() != null && !ticketCategory.getDescription().isBlank()) {
			fieldsToUpdate.add("description = ?");
			params.add(ticketCategory.getDescription());
		}
		if (ticketCategory.getQuantity() != 0) {
			fieldsToUpdate.add("quantity = ?");
			params.add(ticketCategory.getQuantity());
		}

		updateQuery.append(String.join(", ", fieldsToUpdate));
		updateQuery.append(" WHERE id = ? AND event_id = ?");
		params.add(ticketCategory.getId());
		params.add(ticketCategory.getEventId());

		jdbc.update(updateQuery.toString(), params.toArray());

		return ticketCategory;
	}

	@Override
	public Collection<TicketCategory> findTicketCategoriesFromEvent(Long eventId) {
		String querySql = """
				    SELECT id, price, description, quantity, quantity_available, event_id, created_at
				    FROM tb_ticket_category
				    WHERE event_id = ?
				    ORDER BY id
				""";

		Collection<TicketCategory> ticketCategories = jdbc.query(querySql, this::mapTicketCategory, eventId);

		return ticketCategories;
	}

	@Override
	public TicketCategory findTicketCategoryById(Long ticketCategoryId) {
		String querySql = """
				    SELECT id, price, description, quantity, quantity_available, event_id, created_at
				    FROM tb_ticket_category
				    WHERE id = ?
				""";

		return jdbc.queryForObject(querySql, this::mapTicketCategory, ticketCategoryId);
	}

	@Override
	public Boolean reserveTicket(Long ticketCategoryId, int quantity) {
		String sql = "UPDATE tb_ticket_category " + "SET quantity_available = quantity_available - ? "
				+ "WHERE id = ? AND quantity_available >= ?";
		int rows = jdbc.update(sql, quantity, ticketCategoryId, quantity);
		return rows > 0;
	}

	@Override
	public Boolean releaseTicket(Long ticketCategoryId, int quantity) {
		String sql = "UPDATE tb_ticket_category " + "SET quantity_available = quantity_available + ? "
				+ "WHERE id = ? AND (quantity_available + ?) <= quantity";
		int rows = jdbc.update(sql, quantity, ticketCategoryId, quantity);
		return rows > 0;
	}

	private TicketCategory mapTicketCategory(ResultSet rs, int rowNum) throws SQLException {
		return new TicketCategory(rs.getLong("id"), rs.getLong("price"), rs.getString("description"),
				rs.getInt("quantity"), rs.getInt("quantity_available"), rs.getLong("event_id"),
				DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at")));
	}

}
