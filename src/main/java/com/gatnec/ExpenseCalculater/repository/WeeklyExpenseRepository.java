package com.gatnec.ExpenseCalculater.repository;

import com.gatnec.ExpenseCalculater.entities.WeeklyExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyExpenseRepository extends JpaRepository<WeeklyExpenses, Long> {
}
