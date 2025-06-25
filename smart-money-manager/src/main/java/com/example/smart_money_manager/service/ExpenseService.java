package com.example.smart_money_manager.service;

import com.example.smart_money_manager.model.Expense;
import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense, User user) {
        expense.setUser(user);
        expense.setDate(LocalDate.now());
        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(User user) {
        return expenseRepository.findByUser_Id(user.getId());
    }
}
