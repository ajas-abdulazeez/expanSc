package com.gatnec.ExpenseCalculater.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "weekly_expenses")
public class WeeklyExpenses extends Expenses{

    @ManyToOne
    @JsonIgnore // Add this annotation to break the circular reference
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

}
