package br.selene.projectseleneback.infra.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.infra.auth.dto.LoginResponseDTO;
import br.selene.projectseleneback.infra.auth.dto.LoginUserDTO;
import br.selene.projectseleneback.infra.auth.dto.RegisterUserDTO;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> register(@RequestBody RegisterUserDTO registerUserDto) {
        Customer registeredCustomer = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}