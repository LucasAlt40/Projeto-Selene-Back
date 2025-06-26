package br.selene.projectseleneback.infra.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public Page<Customer> findAll(Pageable pageable) {
		 String rowCountSql = "SELECT count(*) AS row_count " +
	                "FROM tb_customer";
		 
		 int total = jdbc.queryForObject(rowCountSql, Integer.class);
	                
		 
		 String querySql = "SELECT id, document, name, email, phone " +
	                "FROM tb_customer " +
	                "LIMIT " + pageable.getPageSize() + " " +
	                "OFFSET " + pageable.getOffset();
		 
		 List<Customer> customers = jdbc.query(querySql, this::mapRow);
		
		 return new PageImpl<Customer>(customers, pageable, total);
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
