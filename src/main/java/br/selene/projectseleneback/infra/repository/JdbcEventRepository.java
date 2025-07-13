package br.selene.projectseleneback.infra.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.selene.projectseleneback.domain.event.Address;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.EventStatusEnum;
import br.selene.projectseleneback.domain.event.TicketCategory;
import br.selene.projectseleneback.domain.event.dto.SearchEventDTO;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import br.selene.projectseleneback.infra.utils.DateHelper;

@Repository
public class JdbcEventRepository implements IEventRepository {

	private final JdbcTemplate jdbc;

	public JdbcEventRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Page<Event> findAll(SearchEventDTO searchEventDTO) {
		Pageable pageable = PageRequest.of(Math.max(searchEventDTO.getPageOrDefault() - 1, 0),
				searchEventDTO.getPageSizeOrDefault());

		String rowCountSql = "SELECT count(*) FROM tb_event";
		int total = jdbc.queryForObject(rowCountSql, Integer.class);

		String querySql = """
				    SELECT id, title, description, date, city, neighbourhood, number, street, state, zip_code, status, created_at, preview_image_url
				    FROM tb_event
				    ORDER BY id
				    LIMIT ? OFFSET ?
				""";

		List<Event> events = jdbc.query(querySql, this::mapEvent, pageable.getPageSize(), pageable.getOffset());

		return new PageImpl<>(events, pageable, total);
	}

	@Override
	public Event findById(Long eventId) {
		String querySql = """
				    SELECT id, title, description, date, city, neighbourhood, number, street, state, zip_code, status, created_at, preview_image_url
				    FROM tb_event
				    WHERE id = ?
				""";

		return jdbc.queryForObject(querySql, this::mapEvent, eventId);
	}

	@Override
	public Event save(Event event) {
		Long eventId = event.getId();

		if (eventId != null) {
			updateEvent(event);
			return event;
		}

		createEvent(event);
		return event;
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
		return new TicketCategory(
						rs.getLong("id"), 
						rs.getLong("price"), 
						rs.getString("description"),
						rs.getInt("quantity"), 
						rs.getInt("quantity_available"), 
						rs.getLong("event_id"),
						DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at"))
					);
	}

	private Event mapEvent(ResultSet rs, int rowNum) throws SQLException {
		Address address = new Address();

		address.setCity(rs.getString("city"));
		address.setNeighbourhood(rs.getString("neighbourhood"));
		address.setNumber(rs.getString("number"));
		address.setStreet(rs.getString("street"));
		address.setState(rs.getString("state"));
		address.setZipCode(rs.getString("zip_code"));

		return new Event(
					rs.getLong("id"), 
					rs.getString("title"), 
					rs.getString("description"),
					DateHelper.convertDateToLocalDateTime(rs.getTimestamp("date")), 
					address, rs.getString("preview_image_url"),
					EventStatusEnum.valueOf(rs.getString("status")),
					DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at"))
				);
	}

	private void createEvent(Event event) {
		String sql = """
					INSERT INTO tb_event(title, description, date, city, neighbourhood, number, street, state, zip_code, status, preview_image_url)
					VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, event.getTitle());
			ps.setString(2, event.getDescription());
			ps.setDate(3, Date.valueOf(event.getDate().toLocalDate()));
			ps.setString(4, event.getAddress().getCity());
			ps.setString(5, event.getAddress().getNeighbourhood());
			ps.setString(6, event.getAddress().getNumber());
			ps.setString(7, event.getAddress().getStreet());
			ps.setString(8, event.getAddress().getState());
			ps.setString(9, event.getAddress().getZipCode());
			ps.setString(10, event.getStatus().name());
			ps.setString(10, event.getPreviewImageUrl());
			return ps;
		}, keyHolder);

		Number generatedId = (Number) keyHolder.getKeys().get("id");
		if (generatedId != null) {
			event.setId(generatedId.longValue());
		}
	}

	private void updateEvent(Event event) {
		StringBuilder updateQuery = new StringBuilder("UPDATE tb_event SET ");
		List<String> fieldsToUpdate = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		if (event.getTitle() != null && !event.getTitle().isBlank()) {
			fieldsToUpdate.add("title = ?");
			params.add(event.getTitle());
		}
		if (event.getDescription() != null && !event.getDescription().isBlank()) {
			fieldsToUpdate.add("description = ?");
			params.add(event.getDescription());
		}
		if (event.getDate() != null) {
			fieldsToUpdate.add("date = ?");
			params.add(Date.valueOf(event.getDate().toLocalDate()));
		}

		if (event.getAddress() != null) {
			Address address = event.getAddress();
			if (address.getCity() != null && !address.getCity().isBlank()) {
				fieldsToUpdate.add("city = ?");
				params.add(address.getCity());
			}

			if (address.getNeighbourhood() != null && !address.getNeighbourhood().isBlank()) {
				fieldsToUpdate.add("neighbourhood = ?");
				params.add(address.getNeighbourhood());
			}

			if (address.getNumber() != null && !address.getNumber().isBlank()) {
				fieldsToUpdate.add("number = ?");
				params.add(address.getNumber());
			}

			if (address.getStreet() != null && !address.getStreet().isBlank()) {
				fieldsToUpdate.add("street = ?");
				params.add(address.getStreet());
			}

			if (address.getState() != null && !address.getState().isBlank()) {
				fieldsToUpdate.add("state = ?");
				params.add(address.getState());
			}

			if (address.getZipCode() != null && !address.getZipCode().isBlank()) {
				fieldsToUpdate.add("zip_code = ?");
				params.add(address.getZipCode());
			}

		}
		
		if(event.getStatus() != null) {
			fieldsToUpdate.add("status = ?");
			params.add(event.getStatus().name());
		}
		
		if(event.getPreviewImageUrl() != null && !event.getPreviewImageUrl().isBlank()) {
			fieldsToUpdate.add("preview_image_url = ?");
			params.add(event.getPreviewImageUrl());
		}

		updateQuery.append(String.join(", ", fieldsToUpdate));
		updateQuery.append(" WHERE id = ?");
		params.add(event.getId());

		jdbc.update(updateQuery.toString(), params.toArray());
	}

}
