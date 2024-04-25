package com.example.demo.repo;

import com.example.demo.models.StoreProduct;
import org.springframework.data.repository.CrudRepository;

public interface StoreProductRepository extends CrudRepository<StoreProduct, String> {
}
