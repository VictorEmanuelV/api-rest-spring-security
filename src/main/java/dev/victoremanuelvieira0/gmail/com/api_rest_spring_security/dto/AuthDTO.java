package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
