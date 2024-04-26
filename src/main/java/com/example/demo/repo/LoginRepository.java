package com.example.demo.repo;

import com.example.demo.models.Employee;
import com.example.demo.models.Login;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends CrudRepository<Login, String> {
    @Query("SELECT c FROM Login c WHERE c.login = :login and c.password = :password")
    Login findByLoginAndPassword(@Param("login") String login, @Param("password") String password);


}
