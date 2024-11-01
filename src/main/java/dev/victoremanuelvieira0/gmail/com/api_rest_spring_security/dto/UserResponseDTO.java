package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private RoleEnum role;
}
