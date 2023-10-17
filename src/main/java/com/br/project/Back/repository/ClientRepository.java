package com.br.project.Back.repository;

import com.br.project.Back.model.Client;
import com.br.project.Back.model.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM client WHERE id = :id")
    Client getById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM client c " +
            "WHERE (LOWER(CONCAT(c.id, c.name, c.email)) LIKE LOWER(CONCAT('%', :value ,'%')) OR :value IS NULL)")
    Page<Client> search(@Param("value") String value, Pageable pageable);
}
