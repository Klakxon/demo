package com.example.demo.repo;

import com.example.demo.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findByName(@Param("name") String name);

    @Query("SELECT c.name, COUNT(p.id) AS Number_of_products FROM Category c LEFT JOIN Product p ON c.id = p.id_category GROUP BY c.name")
    List<Object[]> numberOfProductsInEachCategory();

    @Query("SELECT c.name AS category_name, COUNT(*) AS number_products\n" +
            "FROM Product p JOIN Category c ON p.id_category = c.id\n" +
            "WHERE c.name = :name GROUP BY c.name")
    List<Object[]> numberOfProductsInCategory(@Param("name") String name);
}
