package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
    private RoleEnum role;

}
