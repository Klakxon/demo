package com.example.demo.repo;

import com.example.demo.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findByName(@Param("name") String name);
}
