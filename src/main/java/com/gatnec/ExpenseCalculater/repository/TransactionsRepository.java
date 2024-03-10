package com.gatnec.ExpenseCalculater.repository;

import com.gatnec.ExpenseCalculater.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {

    List<Transactions> findByUserUserId(Long userId);

}
