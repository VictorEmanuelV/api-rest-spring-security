package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.AuthDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    void authenticate(AuthDTO dto, AuthenticationManager manager);
    String generateToken(AuthDTO dto);
}
