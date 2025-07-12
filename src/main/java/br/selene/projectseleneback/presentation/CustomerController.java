package br.selene.projectseleneback.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.CreateCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.SearchCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.UpdateCustomerDTO;
import br.selene.projectseleneback.infra.services.CustomerService;
import br.selene.projectseleneback.presentation.utils.CustomPage;

@RestController
@RequestMapping(MappingEndpoint.Customer.MAIN)
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping(MappingEndpoint.FIND)
	@ResponseStatus(HttpStatus.OK)
	public CustomPage<Customer> findAll(SearchCustomerDTO searchCustomerDTO){
		return new CustomPage<Customer>(customerService.findAll(searchCustomerDTO));
	}
	
	@PostMapping(MappingEndpoint.CREATE)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@RequestBody CreateCustomerDTO createCustomerDTO ) {
		return customerService.create(createCustomerDTO);
	}
	
	@PostMapping("/{id}" + MappingEndpoint.UPDATE)
	@ResponseStatus(HttpStatus.OK)
	public Customer update(@PathVariable Long id, @RequestBody UpdateCustomerDTO updateCustomerDTO) {
		return customerService.update(id, updateCustomerDTO);
	}
	
}
