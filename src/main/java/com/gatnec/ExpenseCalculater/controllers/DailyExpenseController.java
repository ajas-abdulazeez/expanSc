package com.gatnec.ExpenseCalculater.controllers;

import com.gatnec.ExpenseCalculater.dto.DailyExpensesSummaryDTO;
import com.gatnec.ExpenseCalculater.entities.DailyExpenses;
import com.gatnec.ExpenseCalculater.entities.Transactions;
import com.gatnec.ExpenseCalculater.service.DailyExpensesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/daily-expenses")
public class DailyExpenseController {


    private final DailyExpensesServiceImpl dailyExpensesService;

    @Autowired
    public DailyExpenseController(DailyExpensesServiceImpl dailyExpensesService) {
        this.dailyExpensesService = dailyExpensesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyExpenses> getDailyExpensesById(@PathVariable Long id) {
        Optional<DailyExpenses> dailyExpensesOptional = dailyExpensesService.getDailyExpensesById(id);
        return dailyExpensesOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DailyExpenses>> getAllDailyExpensesByUserId(@PathVariable Long userId) {
        List<DailyExpenses> dailyExpensesList = dailyExpensesService.getAllDailyExpensesByUserId(userId);
        return ResponseEntity.ok(dailyExpensesList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyExpenses(@PathVariable Long id) {
        dailyExpensesService.deleteDailyExpenses(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{dailyExpensesId}/transactions")
    public ResponseEntity<Void> addTransaction(@PathVariable Long dailyExpensesId, @RequestBody Transactions transaction) {
        dailyExpensesService.addTransaction(dailyExpensesId, transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> addTransaction(@RequestParam("date") LocalDate date, @RequestBody Transactions transaction, @RequestParam("userId") Long userId) {
        dailyExpensesService.addTransaction(date, transaction, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/filter/summary")
    public ResponseEntity<List<DailyExpensesSummaryDTO>> getDailyExpensesSummary(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DailyExpensesSummaryDTO> summaryList = dailyExpensesService.getDailyExpensesSummaryFilterByDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(summaryList);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<DailyExpensesSummaryDTO>> getDailyExpensesSummary(@RequestParam("userId") Long userId) {
        List<DailyExpensesSummaryDTO> summaryList = dailyExpensesService.getDailyExpensesSummary(userId);
        return ResponseEntity.ok(summaryList);
    }
}
