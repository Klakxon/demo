package com.example.demo.repo;

import com.example.demo.models.Sale;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, String> {
}
