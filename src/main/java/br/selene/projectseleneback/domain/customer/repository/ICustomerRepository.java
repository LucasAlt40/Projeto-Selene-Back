package br.selene.projectseleneback.domain.customer.repository;

import java.util.List;

import br.selene.projectseleneback.domain.customer.Customer;

public interface ICustomerRepository {
	
	public List<Customer> findAll();
	
	public Customer findById(int customerId);
	
	public Customer findByEmail(String customerEmail);
	
}
