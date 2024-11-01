package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ValidatorError extends StandardError{
    private List<FieldMessage> erros = new ArrayList<>();

    public void addErrors(BindingResult result){
       for(FieldError f:result.getFieldErrors()){
           erros.add(new FieldMessage(f.getField(),f.getDefaultMessage()));
       }
    }

    public static ValidatorError create(){
        return new ValidatorError();
    }
}
