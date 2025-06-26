package br.selene.projectseleneback.domain.customer.repository;

import br.selene.projectseleneback.domain.customer.Customer;

public interface ICustomerRepository {
	
	public Iterable<Customer> findAll();
	
	public Customer findById(int customerId);
	
	public Customer findByEmail(String customerEmail);
	
}
