package ru.zaichko.vacationPayCalculator.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class Calculator {
    private Double averageSalary;
    private LocalDate startDateOfVacation;
    private LocalDate endDateOfVacation;
}
