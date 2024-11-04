package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.AuthDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.UserNotExistsException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.repository.UserRepository;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {
    private static final Long ID = 1L;
    private static final String NAME = "TESTE";
    private static final String EMAIL = "TESTE@email.com";
    private static final String PASSWORD = "123456";
    private static final RoleEnum ROLE = RoleEnum.ADMIN;
    private static final String TOKEN = "GENERATED TOKEN";
    private User user;
    private AuthDTO dto;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository repository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        startsUser();
    }

    @Test
    void authenticate() {
        var auth = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
        authenticationService.authenticate(dto,authenticationManager);
        verify(authenticationManager,times(1)).authenticate(auth);
    }

    @Test
    void generateToken() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(jwtService.createToken(any())).thenReturn(TOKEN);

        String token = authenticationService.generateToken(dto);

        assertNotNull(token);
        assertEquals(token,TOKEN);
    }
    @Test
    void generateTokenException() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotExistsException.class,()->{
            authenticationService.generateToken(dto);
        });
    }

    @Test
    void loadUserByUsername() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));

        UserDetails details = authenticationService.loadUserByUsername(dto.getEmail());
        assertNotNull(details);
        verify(repository).findByEmail(anyString());

        assertEquals(details.getUsername(),user.getEmail());
        assertEquals(details.getPassword(),user.getPassword());
    }
    @Test
    void loadUserByUsernameException() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,()->{
            authenticationService.loadUserByUsername(EMAIL);
        });
    }
    void startsUser(){
        UserDetails userDetails = mock(UserDetails.class);
        this.dto = new AuthDTO(EMAIL,PASSWORD);
        this.user = new User(ID,NAME,EMAIL,PASSWORD,ROLE);
    }
}