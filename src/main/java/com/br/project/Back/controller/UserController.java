package com.br.project.Back.controller;

import com.br.project.Back.model.User;
import com.br.project.Back.model.dto.UserDTO;
import com.br.project.Back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public void saveUser(@RequestBody @Validated UserDTO user){
        try{
            this.service.saveUser(user);
        }catch (Exception e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        return service.findById(id);
    }
}