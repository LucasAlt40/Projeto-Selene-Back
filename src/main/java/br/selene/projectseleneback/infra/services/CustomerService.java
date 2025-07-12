package br.selene.projectseleneback.infra.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.CreateCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.SearchCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.UpdateCustomerDTO;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;

@Service
public class CustomerService {
	
	private final ICustomerRepository customerRepository;
	
	public CustomerService(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Page<Customer> findAll(SearchCustomerDTO searchCustomerDTO){
		return customerRepository.findAll(searchCustomerDTO);
	}
	
	public Customer create(CreateCustomerDTO createCustomerDTO) {
		Customer newCustomer = new Customer();
		
		newCustomer.setDocument(createCustomerDTO.document());
		newCustomer.setName(createCustomerDTO.name());
		newCustomer.setEmail(createCustomerDTO.email());
		newCustomer.setPhone(createCustomerDTO.phone());
		newCustomer.setPassword(createCustomerDTO.password());
		
		return customerRepository.save(newCustomer);
	}
	
	public Customer update(Long customerId, UpdateCustomerDTO updateCustomerDTO) {
		Customer newCustomer = new Customer();
		
		newCustomer.setId(customerId);
		newCustomer.setDocument(updateCustomerDTO.document());
		newCustomer.setName(updateCustomerDTO.name());
		newCustomer.setEmail(updateCustomerDTO.email());
		newCustomer.setPhone(updateCustomerDTO.phone());
		newCustomer.setPassword(updateCustomerDTO.password());
		
		return customerRepository.save(newCustomer);
	}

	public Customer findById(Long customerId) {
		return  new Customer();
	}
	
}
