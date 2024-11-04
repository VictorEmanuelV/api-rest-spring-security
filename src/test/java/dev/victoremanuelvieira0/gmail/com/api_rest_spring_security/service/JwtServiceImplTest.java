package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {
    private static final Long ID = 1L;
    private static final String NAME = "TESTE";
    private static final String EMAIL = "TESTE@email.com";
    private static final String PASSWORD = "123456";
    private static final RoleEnum ROLE = RoleEnum.ADMIN;

    private User user;

    @InjectMocks
    private JwtServiceImpl jwtService;
    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void createToken() {
       String token =  jwtService.createToken(user);
        assertTrue(token.contains("."));
        assertNotNull(token);

    }

    @Test
    void validToken() {
        String token = jwtService.createToken(user);
        String email = jwtService.validToken(token);
        assertEquals(email,user.getEmail());
    }
    void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD, ROLE);
    }
}