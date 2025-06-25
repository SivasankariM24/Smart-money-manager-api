package com.example.smart_money_manager.controller;

import com.example.smart_money_manager.model.Income;
import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping
    public Income addIncome(@RequestBody Income income, @AuthenticationPrincipal User user) {
        return incomeService.addIncome(income, user);
    }

    @GetMapping
    public List<Income> getUserIncomes(@AuthenticationPrincipal User user) {
        return incomeService.getUserIncomes(user);
    }
}
