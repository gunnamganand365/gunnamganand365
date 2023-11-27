package com.employee.Demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee ID cannot be null")
    @NotBlank(message = "Employee ID cannot be blank")
    @Column(name = "employee_id", nullable = false, unique = true)
    private String employeeId;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 1, message = "At least one phone number is required")
    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone_number")
    private List<String> phoneNumbers;

    @NotBlank(message = "DOJ cannot be blank")
    @Column(name = "doj", nullable = false)
    private String doj;

    @Positive(message = "Salary must be a positive value")
    @Column(name = "salary", nullable = false)
    private double salary;

}
