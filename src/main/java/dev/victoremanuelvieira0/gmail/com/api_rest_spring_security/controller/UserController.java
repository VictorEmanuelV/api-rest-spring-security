package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.controller;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserCreateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserResponseDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto){

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUserById(@PathVariable Long id){

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@RequestBody UserUpdateDTO dto){

    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){

    }
}
