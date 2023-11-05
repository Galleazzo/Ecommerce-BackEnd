package com.br.project.Back.controller;

import com.br.project.Back.config.FirebaseGateway;
import com.br.project.Back.model.User;
import com.br.project.Back.model.dto.UserAuthDTO;
import com.br.project.Back.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    private FirebaseGateway firebaseGateway = new FirebaseGateway();


    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody UserAuthDTO userDTO) throws Exception{
        try {
            return this.firebaseGateway.login(userDTO);
        } catch (Exception e) {
            return new ResponseEntity<>("ERRO!", HttpStatus.BAD_GATEWAY);
        }

    }

    private UserAuthDTO convertuserDTOTouserAuthDTO(UserDTO userDTO){
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setEmail(userDTO.getEmail());
        userAuthDTO.setPassword(userDTO.getPassword());
        return userAuthDTO;
    }
}
