package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.controller;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.AuthDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<String> authentication(@Valid @RequestBody AuthDTO dto){
        authenticationService.authenticate(dto,manager);
       String token =  authenticationService.generateToken(dto);
       return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
