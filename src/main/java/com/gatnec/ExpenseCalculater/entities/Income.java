package com.gatnec.ExpenseCalculater.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "income")
@Inheritance(strategy = InheritanceType.JOINED)
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_income_amount")
    protected double totalAmount;

    @Column(name = "income_date")
    protected LocalDate date;
}
