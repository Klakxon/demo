package com.example.demo.repo;

import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT c FROM Employee c WHERE c.phone = :phone")
    Employee findByPhone(@Param("phone") String phone);
}
