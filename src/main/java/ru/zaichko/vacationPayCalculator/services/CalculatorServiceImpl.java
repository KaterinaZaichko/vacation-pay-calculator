package ru.zaichko.vacationPayCalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zaichko.vacationPayCalculator.models.Calculator;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    public static final String FORMAT_OF_VACATION_PAYMENTS = "#0.00";
    public static final double AVERAGE_AMOUNT_DAY_IN_MONTH = 29.3;
    private final Calculator calculator;

    @Override
    public String getResult(Double averageSalary, LocalDate startDateOfVacation, LocalDate endDateOfVacation) {
        String result = "0";
        if (averageSalaryIsValid(averageSalary)
                && startDateOfVacationIsValid(startDateOfVacation)
                && endDateOfVacationIsValid(startDateOfVacation, endDateOfVacation)) {
            calculator.setAverageSalary(averageSalary);
            calculator.setStartDateOfVacation(startDateOfVacation);
            calculator.setEndDateOfVacation(endDateOfVacation);
            result = new DecimalFormat(FORMAT_OF_VACATION_PAYMENTS).format(
                    calculator.getAverageSalary() / AVERAGE_AMOUNT_DAY_IN_MONTH * getAmountVacationDays());
        }
        return result;
    }

    private boolean averageSalaryIsValid(Double averageSalary) {
        return averageSalary != null && averageSalary > 0;
    }

    private boolean startDateOfVacationIsValid(LocalDate startDateOdVacation) {
        return startDateOdVacation != null;
    }

    private boolean endDateOfVacationIsValid(LocalDate startDateOdVacation, LocalDate endDateOdVacation) {
        return endDateOdVacation != null && endDateOdVacation.isAfter(startDateOdVacation);
    }

    private int getAmountVacationDays() {
        int amountVacationDays = 0;
        if (calculator.getStartDateOfVacation() != null && calculator.getEndDateOfVacation() != null &&
                (calculator.getStartDateOfVacation().isEqual(calculator.getEndDateOfVacation())
                        || calculator.getStartDateOfVacation().isBefore(calculator.getEndDateOfVacation()))) {
            for (LocalDate i = calculator.getStartDateOfVacation();
                 !i.isAfter(calculator.getEndDateOfVacation());
                 i = i.plusDays(1)) {
                if (!isHoliday(i)) {
                    amountVacationDays++;
                }
            }
        }
        return amountVacationDays;
    }

    private boolean isHoliday(LocalDate localDate) {
        return isWeekend(localDate) || PublicHolidays.publicHolidays.contains(toMonthDay(localDate));
    }

    private boolean isWeekend(LocalDate localDate) {
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static MonthDay toMonthDay(LocalDate localDate) {
        return MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
    }

    private static class PublicHolidays {
        public final static Set<MonthDay> publicHolidays = new HashSet<>();

        static {
            publicHolidays.add(MonthDay.of(Month.JANUARY, 1));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 2));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 3));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 4));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 5));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 6));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 7));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 8));
            publicHolidays.add(MonthDay.of(Month.FEBRUARY, 23));
            publicHolidays.add(MonthDay.of(Month.MARCH, 8));
            publicHolidays.add(MonthDay.of(Month.MAY, 1));
            publicHolidays.add(MonthDay.of(Month.MAY, 9));
            publicHolidays.add(MonthDay.of(Month.JUNE, 12));
            publicHolidays.add(MonthDay.of(Month.NOVEMBER, 4));
        }
    }
}
