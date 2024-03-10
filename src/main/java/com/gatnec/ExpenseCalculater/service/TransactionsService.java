package com.gatnec.ExpenseCalculater.service;

import com.gatnec.ExpenseCalculater.entities.Transactions;
import com.gatnec.ExpenseCalculater.entities.User;
import com.gatnec.ExpenseCalculater.repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionRepository;

    // Inject User Service to fetch user information
    private final UserServiceImpl userService;

    public TransactionsService(TransactionsRepository transactionRepository, UserServiceImpl userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public List<Transactions> getAllTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserUserId(userId);
    }

    public Optional<Transactions> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transactions createTransaction(Long userId, Transactions transaction) {
        // Fetch user information
        Optional<User> userOptional = userService.getUserById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        // Associate transaction with user
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
