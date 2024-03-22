package br.com.estudotestes.api.services.impl;


import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.domain.dto.UsersDTO;
import br.com.estudotestes.api.repositories.UserRepository;
import br.com.estudotestes.api.services.UserService;
import br.com.estudotestes.api.services.exceptions.DataIntegratyViolationException;
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
        findByEmail(usersDTO);
        return userRepository.save(mapper.map(usersDTO, Users.class));
    }

    @Override
    public Users update(UsersDTO usersDTO) {
        Users users = this.findById(usersDTO.getId());
        users.setEmail(usersDTO.getEmail());
        users.setName(usersDTO.getName());
        users.setPassword(usersDTO.getPassword());
        return userRepository.saveAndFlush(users);
    }

    private void findByEmail(UsersDTO usersDTO) {
        Optional<Users> users = userRepository.findByEmail(usersDTO.getEmail());
        if (users.isPresent()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }
    }

}
