package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.controller;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserCreateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserResponseDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserUpdateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id,@Valid @RequestBody UserUpdateDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateById(id,dto));
    }
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(pageable));
    }
}
