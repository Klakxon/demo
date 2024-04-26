package com.example.demo.repo;

import com.example.demo.models.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.phone = :phone")
    Card findByPhone(@Param("phone") String phone);
}
