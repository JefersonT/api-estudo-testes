package br.com.estudotestes.api.config;

import br.com.estudotestes.api.domain.Users;
import br.com.estudotestes.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public List<Users> startDB() {
        Users u1 = new Users(null, "teste1","teste1@udemy.com","123");
        Users u2 = new Users(null, "teste2","teste2@udemy.com","123");
        userRepository.saveAll(List.of(u2,u1));
        return userRepository.findAll();
    }
}
