package br.com.estudotestes.api.services.impl;

import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.domain.dto.UsersDTO;
import br.com.estudotestes.api.repositories.UserRepository;
import br.com.estudotestes.api.services.exceptions.DataIntegratyViolationException;
import br.com.estudotestes.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@gmail.com";
    public static final String PASSWORD = "132";
    public static final String OBJECT_NOT_FOUND = OBJECT_NOT_FOUND;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;
    private Users users;
    private UsersDTO usersDTO;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnUsersInstance() {
        when(userRepository.findById(anyInt()))
                .thenReturn(optionalUsers);

        Users response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try {
            userService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(users));

        List<Users> response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(users);

        Users response = userService.create(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUsers);
        try {
            optionalUsers.get().setId(2);
            userService.create(usersDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUsers);
        try {
            optionalUsers.get().setId(2);
            userService.update(usersDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(users);

        Users response = userService.update(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenDeleteWithSucess() {
        when(userRepository.findById(anyInt()))
                .thenReturn(optionalUsers);
        doNothing().when(userRepository)
                .deleteById(anyInt());
        
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteWithObjectNotFounException() {
        when(userRepository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
        try {
            userService.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    private void startUsers() {
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}