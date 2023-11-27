package com.employee.Demo.repository;

import com.employee.Demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Additional query methods can be added if needed
}