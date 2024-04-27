package com.example.demo.repo;

import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT c FROM Employee c WHERE c.phone = :phone")
    Employee findByPhone(@Param("phone") String phone);

    @Query("SELECT c.role FROM Employee c WHERE c.id = :id")
    String findRoleById(@Param("id") Long id);

    @Query("SELECT e.id, e.surname, e.role \n" +
            "FROM Employee e \n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1 FROM CheckStore cs WHERE cs.id_empl = e.id\n" +
            ") AND NOT EXISTS (\n" +
            "    SELECT 1 FROM Card c WHERE c.surname = e.surname AND c.name = e.name AND c.patronymic = e.patronymic)")
    List<Object[]> findNotInvolved();

}
