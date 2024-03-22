package br.com.estudotestes.api.services.impl;


import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.domain.dto.UsersDTO;
import br.com.estudotestes.api.repositories.UserRepository;
import br.com.estudotestes.api.services.UserService;
import br.com.estudotestes.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users create(UsersDTO usersDTO) {
        return userRepository.save(mapper.map(usersDTO, Users.class));
    }

}
