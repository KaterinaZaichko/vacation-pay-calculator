package ru.zaichko.vacationPayCalculator.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zaichko.vacationPayCalculator.services.CalculatorService;

import java.time.LocalDate;

@Controller
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;

    @GetMapping
    public String calculate(@RequestParam(value = "averageSalary", required = false) Double averageSalary,
                            @RequestParam(value = "startDateOfVacation", required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateOfVacation,
                            @RequestParam(value = "endDateOfVacation", required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDateOfVacation,
                            Model model) {
        model.addAttribute("vacationPayments", calculatorService.getResult(averageSalary,
                startDateOfVacation, endDateOfVacation));
        return "calculate";
    }
}
