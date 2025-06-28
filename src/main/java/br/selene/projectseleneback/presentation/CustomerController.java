package br.selene.projectseleneback.presentation;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.selene.projectseleneback.application.CustomerService;
import br.selene.projectseleneback.domain.customer.Customer;

@RestController
@RequestMapping(MappingEndpoint.Customer.MAIN)
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping(MappingEndpoint.Customer.FIND_ALL)
	public Page<Customer> findAll(){
		return customerService.findAll(null);
	}
	
}
