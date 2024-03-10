package com.gatnec.ExpenseCalculater.controllers;

import com.gatnec.ExpenseCalculater.entities.Transactions;
import com.gatnec.ExpenseCalculater.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {


    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable Long transactionId) {
        Optional<Transactions> transactionOptional = transactionsService.getTransactionById(transactionId);
        return transactionOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transactions>> getAllTransactionsByUserId(@PathVariable Long userId) {
        List<Transactions> transactions = transactionsService.getAllTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Transactions> createTransaction(@PathVariable Long userId, @RequestBody Transactions transaction) {
        Transactions createdTransaction = transactionsService.createTransaction(userId, transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        transactionsService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
