package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage {
     private String fieldName;
     private String message;
}
