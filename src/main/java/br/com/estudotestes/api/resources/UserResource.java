package br.com.estudotestes.api.resources;

import br.com.estudotestes.api.domain.dto.UsersDTO;
import br.com.estudotestes.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    public static final String ID = "/{id}";
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ID)
    public ResponseEntity<UsersDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UsersDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll()
                        .stream().map(user -> mapper.map(user, UsersDTO.class))
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody UsersDTO usersDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ID)
                .buildAndExpand(userService.create(usersDTO).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UsersDTO> update(@PathVariable Integer id, @RequestBody UsersDTO usersDTO) {
        usersDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(userService.update(usersDTO), UsersDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UsersDTO> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
