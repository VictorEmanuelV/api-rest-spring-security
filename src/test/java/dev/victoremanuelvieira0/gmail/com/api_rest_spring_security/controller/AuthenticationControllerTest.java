package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.controller;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.AuthDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    private static final String TOKEN = "GENERATED TOKEN";
    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private AuthenticationService authenticationService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void authentication() {
        AuthDTO dto = new AuthDTO("TESTE@GMAIL.COM","123456");
        doNothing().when(authenticationService).authenticate(any(),any());
        when(authenticationService.generateToken(any())).thenReturn(TOKEN);

        var response = authenticationController.authentication(dto);
        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(),TOKEN);

    }
}