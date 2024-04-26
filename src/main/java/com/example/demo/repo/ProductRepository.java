package com.example.demo.repo;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT c FROM Product c WHERE c.name = :name")
    Product findByName(@Param("name") String name);
}
