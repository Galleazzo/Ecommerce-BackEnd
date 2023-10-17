package com.br.project.Back.service;

import com.br.project.Back.model.User;
import com.br.project.Back.model.dto.UserDTO;
import com.br.project.Back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository repository;

    public User saveUser(UserDTO usuario){
        User user = new User();
        if(usuario.getId() != null)
            user.setId(usuario.getId());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(usuario.getPassword());

        user.setUsername(usuario.getUsername());
        user.setPassword(password);

        return repository.save(user);
    }

    public UserDTO findById(Long id){
        User user = repository.findByIdUniq(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

}