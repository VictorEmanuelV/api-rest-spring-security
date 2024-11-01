package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.impl;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserCreateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserResponseDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserUpdateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.EmailAlreadyExistsException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.UserNotExistsException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.repository.UserRepository;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserResponseDTO saveUser(UserCreateDTO dto) {
        var userEmail = repository.findByEmail(dto.getEmail());

        if(userEmail.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        var user = User.builder()
                  .name(dto.getName())
                  .email(dto.getEmail())
                  .password(dto.getPassword())
                  .role(dto.getRole())
                  .build();

       var response =  repository.save(user);

       return UserResponseDTO.builder()
               .id(response.getId())
               .name(response.getName())
               .email(response.getEmail())
               .password(response.getPassword())
               .role(response.getRole()).build();
    }

    @Override
    public UserResponseDTO findById(Long id) {
        var user =  repository.findById(id).orElseThrow(()->new RuntimeException("not found"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole()).build();
    }

    @Override
    public void deleteUserById(Long id) {
        if(!repository.existsById(id)){
            throw new UserNotExistsException("user not exists");
        }
        repository.deleteById(id);
    }

    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable)
                          .map(x->UserResponseDTO.builder()
                                  .id(x.getId())
                                  .name(x.getName())
                                  .email(x.getEmail())
                                  .password(x.getPassword())
                                  .role(x.getRole())
                                  .build());
    }

    @Override
    public UserResponseDTO updateById(Long id, UserUpdateDTO dto) {
        if(!repository.existsById(id)){
            throw new UserNotExistsException("user not exists");
        }
        var user = repository.findByEmail(dto.getEmail());

        if(user.isPresent()){
            if(!user.get().getId().equals(id)){
                throw new EmailAlreadyExistsException("Email already exists");
            }
        }

      var response =  repository.save(User.builder()
                        .id(id)
                        .name(dto.getName())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .role(dto.getRole())
                        .build());

        return UserResponseDTO.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .password(response.getPassword())
                .role(response.getRole()).build();
    }
}
