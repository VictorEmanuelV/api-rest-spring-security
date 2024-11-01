package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
