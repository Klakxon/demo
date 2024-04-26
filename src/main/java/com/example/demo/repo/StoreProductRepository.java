package com.example.demo.repo;

import com.example.demo.models.StoreProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StoreProductRepository extends CrudRepository<StoreProduct, String> {
    @Query("SELECT c FROM StoreProduct c WHERE c.id_product = :id_product")
    StoreProduct findById_product(@Param("id_product") Long id_product);
}
