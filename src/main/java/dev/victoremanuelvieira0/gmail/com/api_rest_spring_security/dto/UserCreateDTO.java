package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private RoleEnum role;

}
