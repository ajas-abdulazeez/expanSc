package com.gatnec.ExpenseCalculater.repository;

import com.gatnec.ExpenseCalculater.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
