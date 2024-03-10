package com.gatnec.ExpenseCalculater.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "expenses")
@Inheritance(strategy = InheritanceType.JOINED)
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    protected double totalAmount;

    @Column(name = "expense_date")
    protected LocalDate date;


    public Expenses(double amount, LocalDate date) {
        this.totalAmount = amount;
        this.date = date;
    }

    public Expenses() {
    }

    public double getAmount() {
        return totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }
}
