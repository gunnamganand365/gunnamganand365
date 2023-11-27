package com.employee.Demo.controller;

import com.employee.Demo.entity.Employee;
import com.employee.Demo.entity.TaxResponseDTO;
import com.employee.Demo.service.EmployeeService;
import com.employee.Demo.validation.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tax-deductions")
public class TaxDeductionController {

   @Autowired
   private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<TaxResponseDTO>> getTaxDeductions() {
        // Retrieve employee data from your data source
        List<Employee> employees = employeeService.getAllEmployees();

        // Calculate tax deductions for each employee
        List<TaxResponseDTO> taxResponses = employees.stream()
                .map(this::calculateTaxResponse)
                .toList();

        return ResponseEntity.ok(taxResponses);
    }

    private TaxResponseDTO calculateTaxResponse(Employee employee) {
        LocalDate doj = LocalDate.parse(employee.getDoj());
        double yearlySalary = calculateYearlySalary(employee, doj);
        double taxAmount = TaxCalculator.calculateTax(yearlySalary);
        double cessAmount = calculateCessAmount(yearlySalary);


        TaxResponseDTO taxResponse = new TaxResponseDTO();
        taxResponse.setEmployeeId(employee.getEmployeeId());
        taxResponse.setFirstName(employee.getFirstName());
        taxResponse.setLastName(employee.getLastName());
        taxResponse.setYearlySalary(yearlySalary);
        taxResponse.setTaxAmount(taxAmount);
        taxResponse.setCessAmount(cessAmount);

        return taxResponse;
    }

    private double calculateYearlySalary(Employee employee, LocalDate doj) {
        double totalSalary = employee.getSalary();

        // Calculate total salary based on DOJ
        int dojMonth = doj.getMonthValue();
        int currentMonth = LocalDate.now().getMonthValue();

        if (dojMonth <= currentMonth) {
            int numberOfMonths = currentMonth - dojMonth + 1; // Include current month
            totalSalary = totalSalary * numberOfMonths;
        }

        // Adjust for Loss of pay per day
        double lossOfPayPerDay = totalSalary / 30;
        totalSalary = totalSalary - lossOfPayPerDay;

        return totalSalary;
    }

    private double calculateCessAmount(double yearlySalary) {
        double additionalCessThreshold = 2500000;
        double additionalCessRate = 0.02;

        if (yearlySalary > additionalCessThreshold) {
            return (yearlySalary - additionalCessThreshold) * additionalCessRate;
        }

        return 0;
    }
}
