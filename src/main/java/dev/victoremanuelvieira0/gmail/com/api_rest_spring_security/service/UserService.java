package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserCreateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserResponseDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDTO saveUser(UserCreateDTO dto);
    UserResponseDTO findById(Long id);
    void deleteUserById(Long id);
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO updateById(Long id, UserUpdateDTO dto);
}
