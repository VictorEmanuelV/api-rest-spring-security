package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.controller;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserCreateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserResponseDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.dto.UserUpdateDTO;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity.User;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.UserService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
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

    Page<UserResponseDTO> page;
    List<UserResponseDTO> users;
    Pageable pageable;
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void createUser() {
        when(userService.saveUser(any())).thenReturn(userResponseDTO);
        var response = userController.createUser(userCreateDTO);

        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        assertNotNull(response);

        assertEquals(response.getBody().getId(),ID);
        assertEquals(response.getBody().getEmail(),EMAIL);
        assertEquals(response.getBody().getPassword(),PASSWORD);
        assertEquals(response.getBody().getName(),NAME);
        assertEquals(response.getBody().getRole(),RoleEnum.ADMIN);

    }

    @Test
    void findUserById() {
        when(userService.findById(anyLong())).thenReturn(userResponseDTO);
        var response = userController.findUserById(ID);

        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response);

        assertEquals(response.getBody().getId(),ID);
        assertEquals(response.getBody().getEmail(),EMAIL);
        assertEquals(response.getBody().getPassword(),PASSWORD);
        assertEquals(response.getBody().getName(),NAME);
        assertEquals(response.getBody().getRole(),RoleEnum.ADMIN);

    }

    @Test
    void deleteUserById() {
        doNothing().when(userService).deleteUserById(anyLong());
        userController.deleteUserById(ID);
        verify(userService,times(1)).deleteUserById(anyLong());
    }

    @Test
    void updateUserById() {
        when(userService.updateById(anyLong(),any())).thenReturn(userResponseDTO);
        var response = userController.updateUserById(ID,userUpdateDTO);

        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response);

        assertEquals(response.getBody().getId(),ID);
        assertEquals(response.getBody().getEmail(),EMAIL);
        assertEquals(response.getBody().getPassword(),PASSWORD);
        assertEquals(response.getBody().getName(),NAME);
        assertEquals(response.getBody().getRole(),RoleEnum.ADMIN);

    }

    @Test
    void getAllUsers() {
        when(userService.getAllUsers(any())).thenReturn(page);
        var response = userController.getAllUsers(pageable);

        assertNotNull(response);
        assertEquals(response.getBody().getTotalElements(),1);
        assertEquals(response.getClass(), ResponseEntity.class);
        assertEquals(response.getBody().getContent().get(0).getEmail(),EMAIL);
        assertEquals(response.getBody().getContent().get(0).getPassword(),PASSWORD);
        assertEquals(response.getBody().getContent().get(0).getName(),NAME);
        assertEquals(response.getBody().getContent().get(0).getRole(),ROLE);
    }

    void startUser(){
        this.user = new User(ID,NAME,EMAIL,PASSWORD,ROLE);
        this.userCreateDTO = new UserCreateDTO(NAME,EMAIL,PASSWORD,ROLE.name());
        this.userResponseDTO = new UserResponseDTO(ID,NAME,EMAIL,PASSWORD,ROLE);
        this.userUpdateDTO = new UserUpdateDTO(NAME,EMAIL,PASSWORD,ROLE.name());
        this.pageable = PageRequest.of(0,1);
        this.users = new ArrayList<>();
        users.add(userResponseDTO);
        this.page = new PageImpl<>(users);
    }
}