package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.infra.utils.DateHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTicketCategoryRepository implements ITicketCategoryRepository {

	private final JdbcTemplate jdbc;

	public JdbcTicketCategoryRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Page<TicketCategory> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO) {
		Pageable pageable = PageRequest.of(Math.max(searchTicketCategoryDTO.getPageOrDefault() - 1, 0),
				searchTicketCategoryDTO.getPageSizeOrDefault());

		String rowCountSql = "SELECT count(*) FROM tb_ticket_category";
		int total = jdbc.queryForObject(rowCountSql, Integer.class);

		String querySql = """
				    SELECT id, price, description, quantity, created_at, quantity_available
				    FROM tb_ticket_category
				    ORDER BY id
				    LIMIT ? OFFSET ?
				""";

		List<TicketCategory> ticketCategories = jdbc
				.query(querySql, this::mapRow, pageable.getPageSize(), pageable.getOffset());

		return new PageImpl<TicketCategory>(ticketCategories, pageable, total);
	}

	@Override
	public TicketCategory findById(Long ticketCategoryId) {
		return jdbc.queryForObject(
				"select id, price, description, quantity, created_at, quantity_available from tb_ticket_category where id = ?",
				this::mapRow,
				ticketCategoryId);
	}

	@Override
	public TicketCategory save(TicketCategory ticketCategory) {
		Long ticketCategoryId = ticketCategory.getId();

		if (ticketCategoryId != null) {
			updateTicketCategory(ticketCategory);
			return ticketCategory;
		}

		createTicketCategory(ticketCategory);
		return ticketCategory;
	}

	private TicketCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new TicketCategory(
					rs.getLong("id"),
					rs.getLong("price"),
					rs.getString("description"),
					rs.getInt("quantity"),
					DateHelper.convertDateToLocalDateTime(rs.getTimestamp("created_at")),
					rs.getInt("quantity_available")
				);
	}

	private void createTicketCategory(TicketCategory ticketCategory) {
		String sql = """
					INSERT INTO tb_ticket_category(price, description, quantity, quantity_available)
					VALUES (?, ?, ?, ?)
				""";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, ticketCategory.getPrice());
			ps.setString(2, ticketCategory.getDescription());
			ps.setInt(3, ticketCategory.getQuantity());
			ps.setInt(4, ticketCategory.getQuantityAvaliable());
			return ps;
		}, keyHolder);

		Number generatedId = (Number) keyHolder.getKeys().get("id");
		if (generatedId != null) {
			ticketCategory.setId(generatedId.longValue());
		}
	}

	private void updateTicketCategory(TicketCategory ticketCategory) {
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
		updateQuery.append(" WHERE id = ?");
		params.add(ticketCategory.getId());

		jdbc.update(updateQuery.toString(), params.toArray());
	}

}
