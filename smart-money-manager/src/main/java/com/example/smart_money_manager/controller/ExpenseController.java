package com.example.smart_money_manager.controller;

import com.example.smart_money_manager.model.Expense;
import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense, @AuthenticationPrincipal User user) {
        return expenseService.addExpense(expense, user);
    }

    @GetMapping
    public List<Expense> getUserExpenses(@AuthenticationPrincipal User user) {
        return expenseService.getUserExpenses(user);
    }
}
