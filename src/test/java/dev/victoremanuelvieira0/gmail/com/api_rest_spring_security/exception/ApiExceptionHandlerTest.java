package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler.ApiExceptionHandler;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler.StandardError;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler.ValidatorError;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiExceptionHandlerTest {

    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @BeforeEach
    void setUp() {
    }

    @Test
    void emailAlreadyExistsException() {
        EmailAlreadyExistsException ex = mock(EmailAlreadyExistsException.class);
        MockHttpServletRequest mock = new MockHttpServletRequest();

        var response = apiExceptionHandler.emailAlreadyExistsException(ex,mock);

        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getClass(), StandardError.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    void userNotExistsException() {
        UserNotExistsException ex = mock(UserNotExistsException.class);
        MockHttpServletRequest mock = new MockHttpServletRequest();

        var response = apiExceptionHandler.userNotExistsException(ex,mock);

        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getClass(), StandardError.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void methodArgumentNotValidException() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        MockHttpServletRequest mock = new MockHttpServletRequest();
        BindingResult result = mock(BindingResult.class);

        var response = apiExceptionHandler.methodArgumentNotValidException(ex,mock,result);

        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getClass(), ValidatorError.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    void badCredentialsException() {
        BadCredentialsException ex = mock(BadCredentialsException.class);
        MockHttpServletRequest mock = new MockHttpServletRequest();

        var response = apiExceptionHandler.badCredentialsException(ex,mock);

        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getClass(), StandardError.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    void errorCreationTokenException() {
        ErrorCreationTokenException ex = mock(ErrorCreationTokenException.class);
        MockHttpServletRequest mock = new MockHttpServletRequest();

        var response = apiExceptionHandler.errorCreationTokenException(ex,mock);

        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getClass(), StandardError.class);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void errorVerificationTokenException() {
        ErrorVerificationTokenException ex = mock(ErrorVerificationTokenException.class);
        MockHttpServletRequest mock = new MockHttpServletRequest();

        var response = apiExceptionHandler.errorVerificationTokenException(ex,mock);

        assertNotNull(response);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getClass(), StandardError.class);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}