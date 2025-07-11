package br.selene.projectseleneback.infra.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;

@Configuration
public class ApplicationConfiguration {
	private final ICustomerRepository customerRepository;

	public ApplicationConfiguration(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			Customer customer = customerRepository.findByEmail(username);
			if (customer == null) {
				throw new UsernameNotFoundException("User not found");
			}
			return new User(customer);
		};
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		return new DaoAuthenticationProvider(userDetailsService());
	}
}