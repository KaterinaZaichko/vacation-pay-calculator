package ru.zaichko.vacationPayCalculator.services;

import java.time.LocalDate;

public interface CalculatorService {
    String getResult(Double averageSalary, LocalDate startDateOfVacation, LocalDate endDateOfVacation);
}
