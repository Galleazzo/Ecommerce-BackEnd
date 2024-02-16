package com.br.project.Back.controller;

import com.br.project.Back.model.User;
import com.br.project.Back.model.dto.UserDTO;
import com.br.project.Back.model.dto.tokenDTO;
import com.br.project.Back.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws Exception{
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        Authentication authentication =  this.authenticationManager.authenticate(authenticationToken);

        String tokenJWT = this.tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new tokenDTO(tokenJWT));
    }

    @GetMapping("/checkToken")
    public ResponseEntity<Boolean> checkTokenValidity(@RequestParam("token") String token) {
        try {
            boolean isValid = tokenService.cheaakValidToken(token);
            return ResponseEntity.ok(isValid);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
