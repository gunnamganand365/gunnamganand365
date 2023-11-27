package com.employee.Demo.service;

import com.employee.Demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void save(Employee employee);
}
