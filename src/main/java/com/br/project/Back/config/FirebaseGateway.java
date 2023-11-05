package com.br.project.Back.config;

import com.br.project.Back.model.dto.UserAuthDTO;
import com.br.project.Back.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FirebaseGateway {

    @Value("url.google.identitytoolkit")
    private String loginURL;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> login(UserAuthDTO userAuthDTO) {
        try {
            ResponseEntity<Object> result = this.restTemplate.postForEntity(this.loginURL, userAuthDTO, Object.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERRO NA REQUEST", HttpStatus.BAD_REQUEST);
        }
    }
}
