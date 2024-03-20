package br.com.estudotestes.api.services;

import br.com.estudotestes.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
