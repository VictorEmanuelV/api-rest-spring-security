package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;

    RoleEnum(String role){
        this.role = role;
    }


}
