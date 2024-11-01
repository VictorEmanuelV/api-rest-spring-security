package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException(String message){
        super(message);
    }
}
