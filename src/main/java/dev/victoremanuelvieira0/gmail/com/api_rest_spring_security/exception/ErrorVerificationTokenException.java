package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception;

public class ErrorVerificationTokenException extends RuntimeException{
    public ErrorVerificationTokenException(String message){
        super(message);
    }
}
