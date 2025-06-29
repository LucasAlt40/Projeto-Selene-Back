package br.selene.projectseleneback.domain.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.selene.projectseleneback.domain.customer.Customer;

public interface ICustomerRepository {
	
	public Page<Customer> findAll(Pageable pageable);
	
	public Customer findById(int customerId);
	
	public Customer findByEmail(String customerEmail);
	
	public Customer save(Customer customer);
	
}
