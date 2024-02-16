package com.br.project.Back.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.project.Back.model.User;
import com.br.project.Back.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.secret.token.secret}")
    private String secretKey;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

            return JWT.create().withIssuer("E-COMMERCE")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar TOKEN JWT", exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String checkToken(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
            return JWT.require(algorithm)
                    .withIssuer("E-COMMERCE")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }

    public boolean cheaakValidToken(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("E-COMMERCE")
                    .build()
                    .verify(tokenJWT);
            // Verifica se o token está válido
            return true;
        } catch (JWTVerificationException exception){
            // Se ocorrer uma exceção, o token é inválido ou expirou
            return false;
        }
    }
}

