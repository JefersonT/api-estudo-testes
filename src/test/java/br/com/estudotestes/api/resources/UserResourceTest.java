package br.com.estudotestes.api.resources;

import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.domain.dto.UsersDTO;
import br.com.estudotestes.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    public static final int ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@gmail.com";
    public static final String PASSWORD = "132";
    public static final String OBJECT_NOT_FOUND = "Object not found!";
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserServiceImpl userService;

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
    void whenFindByIdThenReturnSuccess() {
        when(userService.findById(anyInt()))
                .thenReturn(users);
        when(mapper.map(any(),any()))
                .thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = userResource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsersDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsers() {
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}