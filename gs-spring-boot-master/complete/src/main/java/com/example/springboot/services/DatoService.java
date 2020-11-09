package com.example.springboot.services;

import java.time.LocalDate;
import java.time.Period;

public class DatoService {
    public LocalDate getDateXMonthsFromDate(LocalDate date, int months){
        LocalDate newDate = date.plusMonths(months);
        return newDate;
    }
    public int getYearsBetweenTwoDates(String date1, String date2){
        LocalDate dateStart = LocalDate.parse(date1);
        LocalDate dateEnd = LocalDate.parse(date2);
        Period period = Period.between(dateStart,dateEnd);
        return period.getYears();
    }
}
