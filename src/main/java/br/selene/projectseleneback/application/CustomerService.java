package br.selene.projectseleneback.application;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;

@Service
public class CustomerService {
	
	private final ICustomerRepository customerRepository;
	
	public CustomerService(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public ArrayList<Customer> findAll(){
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		for(Customer customer : customerRepository.findAll()) {
			customers.add(customer);
		}
		
		return customers;
	}
	
}
