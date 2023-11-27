package com.employee.Demo.validation;

public class TaxCalculator {
    public static double calculateTax(double yearlySalary) {
        double tax = 0;

        if (yearlySalary > 250000 && yearlySalary <= 500000) {
            tax = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            tax = 250000 * 0.05 + (yearlySalary - 500000) * 0.1;
        } else if (yearlySalary > 1000000) {
            tax = 250000 * 0.05 + 500000 * 0.1 + (yearlySalary - 1000000) * 0.2;
        }

        return tax;
    }
}