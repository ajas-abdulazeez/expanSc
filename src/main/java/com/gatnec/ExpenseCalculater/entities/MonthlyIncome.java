package com.gatnec.ExpenseCalculater.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "monthly_income")
public class MonthlyIncome extends Income{


}
