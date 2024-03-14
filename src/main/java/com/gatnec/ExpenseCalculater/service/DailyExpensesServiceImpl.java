package com.gatnec.ExpenseCalculater.service;

import com.gatnec.ExpenseCalculater.dto.DailyExpensesSummaryDTO;
import com.gatnec.ExpenseCalculater.entities.DailyExpenses;
import com.gatnec.ExpenseCalculater.entities.Transactions;
import com.gatnec.ExpenseCalculater.entities.User;
import com.gatnec.ExpenseCalculater.repository.DailyExpenseRepository;
import com.gatnec.ExpenseCalculater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DailyExpensesServiceImpl {

    private final DailyExpenseRepository dailyExpensesRepository;
    private final UserRepository userRepository;

    @Autowired
    public DailyExpensesServiceImpl(DailyExpenseRepository dailyExpensesRepository, UserRepository userRepository) {
        this.dailyExpensesRepository = dailyExpensesRepository;
        this.userRepository = userRepository;
    }

    public Optional<DailyExpenses> getDailyExpensesByDateAndUserId(LocalDate date, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.flatMap(user -> dailyExpensesRepository.findByDateAndUser(date, user));
    }

    public List<DailyExpenses> getAllDailyExpensesByUserId(Long userId) {
        return dailyExpensesRepository.findByUserUserId(userId);
    }

    public Optional<DailyExpenses> getDailyExpensesById(Long id) {
        return dailyExpensesRepository.findById(id);
    }


    public void deleteDailyExpenses(Long id) {
        dailyExpensesRepository.deleteById(id);
    }

    public void addTransaction(Long dailyExpensesId, Transactions transaction) {
        Optional<DailyExpenses> optionalDailyExpenses = dailyExpensesRepository.findById(dailyExpensesId);
        DailyExpenses dailyExpenses = optionalDailyExpenses.orElseThrow(() -> new RuntimeException("Daily Expenses not found"));

        // Assuming you have the user information available, you can set it accordingly
        User user = dailyExpenses.getUser();
        if (user != null) {
            // Add transactions to the appropriate fields based on their type
            switch (transaction.getExpenseType()) {
                // Add cases for other expense types
                case BUS_FAIR:
                    dailyExpenses.getBusFair().add(transaction);
                    break;
                case GROCERIES:
                    dailyExpenses.getGroceries().add(transaction);
                    break;
                case FOOD:
                    dailyExpenses.getFood().add(transaction);
                    break;
                case TRAIN_FAIR:
                    dailyExpenses.getTrainFair().add(transaction);
                    break;
                case SHOPPING:
                    dailyExpenses.getShopping().add(transaction);
                    break;
                case OTHERS:
                    dailyExpenses.getOthers().add(transaction);
                    break;
                default:
                    // Handle unknown expense type
                    break;
            }
            dailyExpensesRepository.save(dailyExpenses);
        } else {
            throw new RuntimeException("User not found for Daily Expenses");
        }
    }

    public void addTransaction(LocalDate todayDate, Transactions transaction, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        Optional<DailyExpenses> optionalDailyExpenses = dailyExpensesRepository.findByDateAndUser(todayDate, user);
        DailyExpenses dailyExpenses;
        if (optionalDailyExpenses.isPresent()) {
            dailyExpenses = optionalDailyExpenses.get();
        } else {
            dailyExpenses = new DailyExpenses();
            dailyExpenses.setDate(todayDate);
            dailyExpenses.setUser(user);
            dailyExpenses.setBusFair(new ArrayList<>());
            dailyExpenses.setFood(new ArrayList<>());
            dailyExpenses.setGroceries(new ArrayList<>());
            dailyExpenses.setShopping(new ArrayList<>());
            dailyExpenses.setTrainFair(new ArrayList<>());
            dailyExpenses.setOthers(new ArrayList<>());
        }

        // Add transactions to the appropriate fields based on their type
        switch (transaction.getExpenseType()) {
            // Add cases for other expense types
            case BUS_FAIR:
                dailyExpenses.getBusFair().add(transaction);
                break;
            case GROCERIES:
                dailyExpenses.getGroceries().add(transaction);
                break;
            case FOOD:
                dailyExpenses.getFood().add(transaction);
                break;
            case TRAIN_FAIR:
                dailyExpenses.getTrainFair().add(transaction);
                break;
            case SHOPPING:
                dailyExpenses.getShopping().add(transaction);
                break;
            case OTHERS:
                dailyExpenses.getOthers().add(transaction);
                break;
            default:
                // Handle unknown expense type
                break;
        }
        dailyExpensesRepository.save(dailyExpenses);
    }


    public List<DailyExpensesSummaryDTO> getDailyExpensesSummaryFilterByDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        List<DailyExpenses> dailyExpensesList = dailyExpensesRepository.findByUserUserIdAndDateBetween(userId, startDate, endDate);
        List<DailyExpensesSummaryDTO> summaryList = new ArrayList<>();

        for (DailyExpenses dailyExpenses : dailyExpensesList) {
            DailyExpensesSummaryDTO summaryDTO = new DailyExpensesSummaryDTO();
            summaryDTO.setDate(dailyExpenses.getDate());
            double totalAmount = 0.0;
            summaryDTO.setDayReference(dailyExpenses.getDayReference());


            totalAmount += dailyExpenses.getBusFair().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getGroceries().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getFood().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getTrainFair().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getShopping().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getOthers().stream().mapToDouble(Transactions::getAmount).sum();

            summaryDTO.setTotalAmount(totalAmount);
            summaryList.add(summaryDTO);
        }

        return summaryList;
    }


    public List<DailyExpensesSummaryDTO> getDailyExpensesSummary(Long userId) {
        List<DailyExpenses> dailyExpensesList = dailyExpensesRepository.findByUserUserId(userId);
        List<DailyExpensesSummaryDTO> summaryList = new ArrayList<>();

        for (DailyExpenses dailyExpenses : dailyExpensesList) {
            DailyExpensesSummaryDTO summaryDTO = new DailyExpensesSummaryDTO();
            summaryDTO.setDate(dailyExpenses.getDate());
            double totalAmount = 0.0;
            summaryDTO.setDayReference(dailyExpenses.getDayReference());

            totalAmount += dailyExpenses.getBusFair().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getGroceries().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getFood().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getTrainFair().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getShopping().stream().mapToDouble(Transactions::getAmount).sum();
            totalAmount += dailyExpenses.getOthers().stream().mapToDouble(Transactions::getAmount).sum();

            summaryDTO.setTotalAmount(totalAmount);
            summaryList.add(summaryDTO);
        }

        return summaryList;
    }
}
