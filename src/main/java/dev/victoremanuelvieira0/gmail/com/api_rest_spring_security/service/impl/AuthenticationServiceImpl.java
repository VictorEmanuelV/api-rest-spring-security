package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.impl;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.AuthDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.repository.UserRepository;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.AuthenticationService;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository repository;
    private AuthenticationManager manager;
    private JwtService jwtService;

    @Override
    public void authenticate(AuthDTO dto) {
        var auth = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
    }
    @Override
    public String generateToken(AuthDTO dto) {
        var user = repository.findByEmail(dto.getEmail()).orElseThrow(()-> new RuntimeException());
        return jwtService.createToken(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Erro ao tentar autenticar usuario não encontrado no banco"));
    }
}
