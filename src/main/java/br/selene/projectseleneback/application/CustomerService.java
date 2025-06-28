package br.selene.projectseleneback.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.customer.Customer;
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
	
}
