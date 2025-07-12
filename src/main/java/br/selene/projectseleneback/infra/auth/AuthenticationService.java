package br.selene.projectseleneback.infra.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;
import br.selene.projectseleneback.infra.auth.dto.LoginUserDTO;
import br.selene.projectseleneback.infra.auth.dto.RegisterUserDTO;

@Service
public class AuthenticationService {
	private final ICustomerRepository customerRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(ICustomerRepository customerRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Customer signup(RegisterUserDTO registerUserDTO) {
		Customer customer = new Customer();
		
		customer.setDocument(registerUserDTO.document());
		customer.setName(registerUserDTO.name());
		customer.setEmail(registerUserDTO.email());
		customer.setPhone(registerUserDTO.phone());
		customer.setPassword(passwordEncoder.encode(registerUserDTO.password()));

        return customerRepository.save(customer);
	}

	public User authenticate(LoginUserDTO loginUserDTO) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginUserDTO.email(), loginUserDTO.password()));

		Customer customer = customerRepository.findByEmail(loginUserDTO.email());
		
		if(customer == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new User(customer);
	}
}
