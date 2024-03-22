package br.com.estudotestes.api.services;

import br.com.estudotestes.api.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
}
