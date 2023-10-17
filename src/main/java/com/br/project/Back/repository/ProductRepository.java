package com.br.project.Back.repository;

import com.br.project.Back.model.Client;
import com.br.project.Back.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE id = :id")
    Product getById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM product c " +
            "WHERE (LOWER(CONCAT(c.id, c.name, c.sku)) LIKE LOWER(CONCAT('%', :value ,'%')) OR :value IS NULL)")
    Page<Product> search(@Param("value") String value, Pageable pageable);
}
