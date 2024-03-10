package com.gatnec.ExpenseCalculater.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "daily_expenses")
public class DailyExpenses extends Expenses {

    @ManyToOne
    @JsonIgnore // Add this annotation to break the circular reference
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bus_fair_id")
    private List<Transactions> busFair;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "groceries_id")
    private List<Transactions> groceries;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "food_id")
    private List<Transactions> food;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "train_fair_id")
    private List<Transactions> trainFair;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shopping_id")
    private List<Transactions> shopping;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "others_id")
    private List<Transactions> others;

    public DailyExpenses(double amount, LocalDate date) {
        super(amount, date);
    }

    public DailyExpenses() {

    }
}
