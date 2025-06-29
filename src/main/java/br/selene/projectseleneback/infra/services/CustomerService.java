package br.selene.projectseleneback.infra.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.CreateCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.UpdateCustomerDTO;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;

@Service
public class CustomerService {
	
	private final ICustomerRepository customerRepository;
	
	public CustomerService(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Page<Customer> findAll(Pageable pageable){
		// TO-DO abstract and simplify pagination		
		if(pageable == null) {
			pageable = new Pageable() {

				@Override
				public int getPageNumber() {
					return 1;
				}

				@Override
				public int getPageSize() {
					return 10;
				}

				@Override
				public long getOffset() {
					return 0;
				}

				@Override
				public Sort getSort() {
					return null;
				}

				@Override
				public Pageable next() {
					return null;
				}

				@Override
				public Pageable previousOrFirst() {
					return null;
				}

				@Override
				public Pageable first() {
					return null;
				}

				@Override
				public Pageable withPage(int pageNumber) {
					return null;
				}

				@Override
				public boolean hasPrevious() {
					return false;
				}
				
			};
		}
		return customerRepository.findAll(pageable);
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
	
}
