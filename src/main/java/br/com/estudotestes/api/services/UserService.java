package br.com.estudotestes.api.services;

import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.domain.dto.UsersDTO;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
    Users create(UsersDTO usersDTO);
    Users update(UsersDTO usersDTO);

    void delete(Integer id);
}
