package com.example.demo.repo;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT c FROM Product c WHERE c.name = :name")
    Product findByName(@Param("name") String name);

    @Query("SELECT c.name, p.name \n" +
            "FROM Category c\n" +
            "LEFT JOIN Product p ON c.id = p.id_category\n" +
            "ORDER BY c.name, p.name\n")
    List<Object[]> sortProduct();
}


