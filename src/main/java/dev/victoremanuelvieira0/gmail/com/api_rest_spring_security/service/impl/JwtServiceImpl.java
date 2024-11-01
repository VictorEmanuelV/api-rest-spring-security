package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.impl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.JwtService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String createToken(User user) {
        try {
            return JWT.create()
                    .withIssuer("api-rest-spring-security")
                    .withSubject(user.getEmail())
                    .withExpiresAt(dateExpire())
                    .sign(algorithm());
        }catch (JWTCreationException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public String validToken(String token) {
        try {
            return JWT.require(algorithm())
                    .withIssuer("api-rest-spring-security")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException ex){
            throw new RuntimeException(ex);
        }
    }
    private Algorithm algorithm(){
        return Algorithm.HMAC256("my-secret");
    }
    private Instant dateExpire(){
        return LocalDateTime.now().plusMinutes(2)
                .atZone(ZoneOffset.of("-03:00"))
                .toInstant();
    }
}
