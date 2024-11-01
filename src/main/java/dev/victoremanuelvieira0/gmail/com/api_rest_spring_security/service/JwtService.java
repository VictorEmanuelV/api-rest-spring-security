package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;

public interface JwtService {
    public String createToken(User user);
    public String validToken(String token);
}
