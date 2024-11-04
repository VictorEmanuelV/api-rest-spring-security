package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserCreateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserResponseDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserUpdateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.EmailAlreadyExistsException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.UserNotExistsException;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.repository.UserRepository;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final Long ID = 1L;
    private static final String NAME = "TESTE";
    private static final String EMAIL = "TESTE@email.com";
    private static final String PASSWORD = "123456";
    private static final RoleEnum ROLE = RoleEnum.ADMIN;
    private static final String CRIPTOPASSWORD = "1@3D7BCA05";

    private User user;

    private UserCreateDTO userCreateDTO;

    private UserResponseDTO userResponseDTO;

    private UserUpdateDTO userUpdateDTO;

    Page<User> page;
    List<User> users;
    Pageable pageable;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
       startUser();
    }

    @Test
    void saveUserShouldReturnUserResponseDTOWithSucess() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn(CRIPTOPASSWORD);
        when(repository.save(any())).thenReturn(user);

        var response = userService.saveUser(userCreateDTO);
        assertNotNull(response);
        assertEquals(UserResponseDTO.class,response.getClass());
        assertEquals(response.getId(),ID);
        assertEquals(response.getEmail(),EMAIL);
        assertEquals(response.getPassword(),PASSWORD);
        assertEquals(response.getName(),NAME);
        assertEquals(response.getRole(),RoleEnum.ADMIN);
    }
    @Test
    void saveUserShouldReturnUserEmailAlreadyExistsException() {
            when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));
            assertThrows(EmailAlreadyExistsException.class,()->{
                userService.saveUser(userCreateDTO);
            });
    }
    @Test
    void findByIdShouldReturnUserResponseDTOWithSucess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        var response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(UserResponseDTO.class,response.getClass());
        assertEquals(response.getId(),ID);
        assertEquals(response.getEmail(),EMAIL);
        assertEquals(response.getPassword(),PASSWORD);
        assertEquals(response.getName(),NAME);
        assertEquals(response.getRole(),RoleEnum.ADMIN);
    }

    @Test
    void deleteUserByIdShouldDeleteAndReturnVoid() {
        when(repository.existsById(anyLong())).thenReturn(true);
        doNothing().when(repository).deleteById(anyLong());
        userService.deleteUserById(ID);
        verify(repository,times(1)).deleteById(anyLong());

    }
    @Test
    void deleteUserByIdShouldDeleteAndReturnUserNotExistsException() {
        when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(UserNotExistsException.class,()->{
            userService.deleteUserById(ID);
        });

    }

    @Test
    void getAllUserShouldReturnPageableOfUsers() {
        when(repository.findAll(pageable)).thenReturn(page);
        var response = userService.getAllUsers(pageable);

        assertNotNull(response);
        assertEquals(1,response.getTotalElements());
        assertEquals(NAME,response.getContent().get(0).getName());
        assertEquals(EMAIL,response.getContent().get(0).getEmail());
        assertEquals(PASSWORD,response.getContent().get(0).getPassword());
        assertEquals(ROLE,response.getContent().get(0).getRole());
    }

    @Test
    void updateByIdShouldUpdateAndReturnUserResponseDTO() {
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));

        when(repository.save(any())).thenReturn(user);

        var response = userService.updateById(ID,userUpdateDTO);


        assertNotNull(response);
        assertEquals(UserResponseDTO.class,response.getClass());
        assertEquals(response.getId(),ID);
        assertEquals(response.getEmail(),EMAIL);
        assertEquals(response.getPassword(),PASSWORD);
        assertEquals(response.getName(),NAME);
        assertEquals(response.getRole(),RoleEnum.ADMIN);

    }
    @Test
    void updateByIdShouldReturnUserNotExistsException() {
        when(repository.existsById(any())).thenReturn(false);

        assertThrows(UserNotExistsException.class,()->{
            userService.updateById(ID,userUpdateDTO);
        });

    }
    @Test
    void updateByIdShouldReturnEmailAlreadyExistsException() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));
        user.setId(2L);
        assertThrows(EmailAlreadyExistsException.class,()->{
            userService.updateById(ID,userUpdateDTO);
        });

    }
    void startUser(){
        this.user = new User(ID,NAME,EMAIL,PASSWORD,ROLE);
        this.userCreateDTO = new UserCreateDTO(NAME,EMAIL,PASSWORD,ROLE.name());
        this.userResponseDTO = new UserResponseDTO(ID,NAME,EMAIL,PASSWORD,ROLE);
        this.userUpdateDTO = new UserUpdateDTO(NAME,EMAIL,PASSWORD,ROLE.name());
        this.pageable = PageRequest.of(0,1);
        this.users = new ArrayList<>();
        users.add(user);
        this.page = new PageImpl<>(users);
    }

}