package br.selene.projectseleneback.presentation;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.CreateCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.UpdateCustomerDTO;
import br.selene.projectseleneback.infra.services.CustomerService;

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
	
	@PostMapping(MappingEndpoint.Customer.CREATE)
	public Customer create(@RequestBody CreateCustomerDTO createCustomerDTO ) {
		return customerService.create(createCustomerDTO);
	}
	
	@PostMapping("/{id}/" + MappingEndpoint.Customer.UPDATE)
	public Customer update(@PathVariable Long id, @RequestBody UpdateCustomerDTO updateCustomerDTO) {
		return customerService.update(id, updateCustomerDTO);
	}
	
}
