package br.selene.projectseleneback.infra.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;

@Repository
public class JdbcCustomerRepository implements ICustomerRepository {
	
	private final JdbcTemplate jdbc;
	
	public JdbcCustomerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Customer> findAll() {
		return jdbc.query("select id, document, name, email, phone from tb_customer", this::mapRow);
	}

	@Override
	public Customer findById(int customerId) {
		return jdbc.queryForObject("select id, document, name, email, phone from tb_customer where id = ?", this::mapRow, customerId);
	}

	@Override
	public Customer findByEmail(String customerEmail) {
		return jdbc.queryForObject("select id, document, name, email, phone from tb_customer where email = ?", this::mapRow, customerEmail);
	}
	
	private Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Customer(
					rs.getInt("id"),
					rs.getString("document"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone")
				);
	}


}
