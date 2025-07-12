package br.selene.projectseleneback.domain.customer.repository;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.SearchCustomerDTO;

public interface ICustomerRepository {
	
	public Page<Customer> findAll(SearchCustomerDTO searchCustomerDTO);
	
	public Customer findById(Long customerId);
	
	public Customer findByEmail(String customerEmail);

	public Customer findByEmailAuth(String customerEmail);

	
	public Customer save(Customer customer);
	
}
