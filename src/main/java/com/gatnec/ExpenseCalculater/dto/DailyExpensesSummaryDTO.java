package com.gatnec.ExpenseCalculater.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyExpensesSummaryDTO {

    private LocalDate date;
    private double totalAmount;
}
