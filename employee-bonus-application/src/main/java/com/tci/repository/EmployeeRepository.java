package com.tci.repository;

import com.tci.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.joiningDate < :date AND e.exitDate > :date")
    List<Employee> findEmployeesForBonus(@Param("date") LocalDate joiningDate);

}
