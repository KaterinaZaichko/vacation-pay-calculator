package ru.zaichko.vacationPayCalculator.services;

import junit.framework.TestCase;
import ru.zaichko.vacationPayCalculator.models.Calculator;

import java.time.LocalDate;

public class CalculatorServiceTest extends TestCase {
    public void testWithValidParametersWithoutHolidaysAndWeekends() {
        CalculatorService calculatorService = new CalculatorServiceImpl(new Calculator());
        String actual = calculatorService.getResult(120000.00,
                LocalDate.of(2023, 5, 15),
                LocalDate.of(2023, 5, 19));
        String expected = "20477,82";
        assertEquals(expected, actual);
    }

    public void testWithValidParametersWithHolidaysAndWeekends() {
        CalculatorService calculatorService = new CalculatorServiceImpl(new Calculator());
        String actual = calculatorService.getResult(120000.00,
                LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 5, 14));
        String expected = "32764,51";
        assertEquals(expected, actual);
    }

    public void testWithNegativeSalary() {
        CalculatorService calculatorService = new CalculatorServiceImpl(new Calculator());
        String actual = calculatorService.getResult(-120000.00,
                LocalDate.of(2023, 5, 22),
                LocalDate.of(2023, 6, 4));
        String expected = "0";
        assertEquals(expected, actual);
    }

    public void testWithInvalidDate() {
        CalculatorService calculatorService = new CalculatorServiceImpl(new Calculator());
        String actual = calculatorService.getResult(120000.00,
                LocalDate.of(2023, 5, 22),
                LocalDate.of(2023, 6, 4));
        String expected = "0";
        assertEquals(expected, actual);
    }
}