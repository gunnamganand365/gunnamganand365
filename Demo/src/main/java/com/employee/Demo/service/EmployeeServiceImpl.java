package com.employee.Demo.service;

import com.employee.Demo.entity.Employee;
import com.employee.Demo.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

}

