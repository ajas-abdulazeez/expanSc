package com.gatnec.ExpenseCalculater.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gatnec.ExpenseCalculater.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne
    @JsonIgnore // Add this annotation to break the circular reference
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @Column(name = "credit_debit")
    private String creditOrDebit;

    @Column(name = "notes")
    private String notes;

    @Column(name = "reference")
    private String reference;

    @Column(name = "transaction_method")
    private String transactionMethod;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transaction_date")
    protected LocalDate date = LocalDate.now();

    // Getters and setters
}