package com.gatnec.ExpenseCalculater.repository;

import com.gatnec.ExpenseCalculater.entities.DailyExpenses;
import com.gatnec.ExpenseCalculater.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyExpenseRepository extends JpaRepository<DailyExpenses,Long> {

    Optional<DailyExpenses> findByDateAndUser(LocalDate date, User user);

    List<DailyExpenses> findByUserUserId(Long userId);

    List<DailyExpenses> findByUserUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

}
