package br.selene.projectseleneback.domain.customer.service;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.dto.CreateCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.SearchCustomerDTO;
import br.selene.projectseleneback.domain.customer.dto.UpdateCustomerDTO;

public interface ICustomerService {

	public Page<Customer> findAll(SearchCustomerDTO searchCustomerDTO);

	public Customer findById(Long customerId);

	public Customer create(CreateCustomerDTO createCustomerDTO);

	public Customer update(Long customerId, UpdateCustomerDTO updateCustomerDTO);

}
