package com.example.smart_money_manager.service;

import com.example.smart_money_manager.model.Income;
import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public Income addIncome(Income income, User user) {
        income.setUser(user);
        income.setDate(LocalDate.now());
        return incomeRepository.save(income);
    }

    public List<Income> getUserIncomes(User user) {
        return incomeRepository.findByUser_Id(user.getId());
    }
}
