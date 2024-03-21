package br.com.estudotestes.api.services;

import br.com.estudotestes.api.domain.Users;

public interface UserService {
    Users findById(Integer id);
}
