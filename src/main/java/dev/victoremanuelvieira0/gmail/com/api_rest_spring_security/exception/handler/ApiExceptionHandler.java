package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.EmailAlreadyExistsException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.UserNotExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<StandardError>emailAlreadyExistsException(EmailAlreadyExistsException ex,
                                                                    HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var error = StandardError.builder()
                    .timestamp(Instant.now())
                    .message(ex.getMessage())
                    .error(status.getReasonPhrase())
                    .Status(status.value())
                    .path(request.getRequestURI())
                    .build();

        return ResponseEntity.status(status.value()).body(error);
    }
    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<StandardError>userNotExistsException(UserNotExistsException ex,
                                                                    HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        var error = StandardError.builder()
                .timestamp(Instant.now())
                .message(ex.getMessage())
                .error(status.getReasonPhrase())
                .Status(status.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidatorError>methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                         HttpServletRequest request, BindingResult result) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        var error = ValidatorError.create();

        error.setMessage("Erro de validação de usuario");
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setError(status.getReasonPhrase());
        error.addErrors(result);


        return ResponseEntity.status(status.value()).body(error);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError>badCredentialsException(BadCredentialsException ex,
                                                                    HttpServletRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        var error = StandardError.builder()
                .timestamp(Instant.now())
                .message(ex.getMessage())
                .error(status.getReasonPhrase())
                .Status(status.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status.value()).body(error);
    }

}
