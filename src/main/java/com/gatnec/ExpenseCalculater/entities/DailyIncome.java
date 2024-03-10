package com.gatnec.ExpenseCalculater.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "daily_income")
public class DailyIncome extends Income{
}
