package br.com.estudotestes.api.services.impl;


import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.repositories.UserRepository;
import br.com.estudotestes.api.services.UserService;
import br.com.estudotestes.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }
}
