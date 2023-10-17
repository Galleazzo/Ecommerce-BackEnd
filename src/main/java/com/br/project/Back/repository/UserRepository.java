package com.br.project.Back.repository;

import com.br.project.Back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE id = :id")
    User findByIdUniq(Long id);
}
