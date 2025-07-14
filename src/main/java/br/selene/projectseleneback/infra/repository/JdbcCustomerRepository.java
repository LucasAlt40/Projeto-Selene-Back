package br.selene.projectseleneback.infra.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.SearchCustomerDTO;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;

@Repository
public class JdbcCustomerRepository implements ICustomerRepository {
	
	private final JdbcTemplate jdbc;
	
	public JdbcCustomerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Page<Customer> findAll(SearchCustomerDTO searchCustomerDTO) {
	    Pageable pageable = PageRequest.of(
	    	Math.max(searchCustomerDTO.getPageOrDefault() - 1, 0),
	        searchCustomerDTO.getPageSizeOrDefault()
	    );

	    String rowCountSql = "SELECT count(*) FROM tb_customer";
	    int total = jdbc.queryForObject(rowCountSql, Integer.class);

	    String querySql = """
	        SELECT id, document, name, email, phone
	        FROM tb_customer
	        ORDER BY id
	        LIMIT ? OFFSET ?
	    """;

	    List<Customer> customers = jdbc.query(
	        querySql,
	        this::mapRow,
	        pageable.getPageSize(),
	        pageable.getOffset()
	    );

	    return new PageImpl<>(customers, pageable, total);
	}


	@Override
	public Customer findById(Long customerId) {
		return jdbc.queryForObject("select id, document, name, email, phone from tb_customer where id = ?", this::mapRow, customerId);
	}

	@Override
	public Customer findByEmail(String customerEmail) {
		return jdbc.queryForObject("select id, document, name, email, phone from tb_customer where email = ?", this::mapRow, customerEmail);
	}

	@Override
	public Customer findByEmailAuth(String customerEmail) {
		return jdbc.queryForObject(
				"select id, document, name, email, phone, password from tb_customer where email = ?",
				(rs, rowNum) -> {
					Customer customer = new Customer(
							rs.getLong("id"),
							rs.getString("document"),
							rs.getString("name"),
							rs.getString("email"),
							rs.getString("phone")
					);
					customer.setPassword(rs.getString("password"));
					return customer;
				},
				customerEmail
		);

	}


	@Override
	public Customer save(Customer customer) {
		Long customerId = customer.getId();
		
		if(customerId != null) {
			updateCustomer(customer);
			return customer;
		}
		
		createCustomer(customer);
		return customer;
	}

	
	private Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Customer(
					rs.getLong("id"),
					rs.getString("document"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone")
				);
	}
	
	private void createCustomer(Customer customer) {
		jdbc.update(
				"insert into tb_customer(document, name, email, phone, password) "
				+ "values (?, ?, ?, ?, ?)", 
				customer.getDocument(),
				customer.getName(),
				customer.getEmail(),
				customer.getPhone(),
				customer.getPassword()
			);
	}
	
	private void updateCustomer(Customer customer) {
	    StringBuilder updateQuery = new StringBuilder("UPDATE tb_customer SET ");
	    List<String> fieldsToUpdate = new ArrayList<>();
	    List<Object> params = new ArrayList<>();

	    if (customer.getName() != null && !customer.getName().isBlank()) {
	        fieldsToUpdate.add("name = ?");
	        params.add(customer.getName());
	    }
	    if (customer.getEmail() != null && !customer.getEmail().isBlank()) {
	        fieldsToUpdate.add("email = ?");
	        params.add(customer.getEmail());
	    }
	    if (customer.getPhone() != null && !customer.getPhone().isBlank()) {
	        fieldsToUpdate.add("phone = ?");
	        params.add(customer.getPhone());
	    }
	    if (customer.getPassword() != null && !customer.getPassword().isBlank()) {
	        fieldsToUpdate.add("password = ?");
	        params.add(customer.getPassword());
	    }

	    updateQuery.append(String.join(", ", fieldsToUpdate));
	    updateQuery.append(" WHERE id = ?");
	    params.add(customer.getId());

	    jdbc.update(
	    		updateQuery.toString(), 
	    		params.toArray()
			);
	}

}
