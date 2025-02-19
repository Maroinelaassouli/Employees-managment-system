package com.ests.ems.repositories;

import com.ests.ems.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
        @Query(value = "SELECT * FROM employee WHERE first_name = :firstName", nativeQuery = true)
        List<Employee> findEmployeesByFirstNameNative(@Param("firstName") String firstName);
        List<Employee> findByLastName(String lastName);
        @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' '," +
                " e.lastName, ' ', e.email, ' ', e.department.name) LIKE %?1%")
        Page<Employee> findBySearchKeyword(String keyword, Pageable pageable);
}
