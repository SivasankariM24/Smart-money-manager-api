package com.example.smart_money_manager.repository;

import com.example.smart_money_manager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
   List<Expense> findByUser_Id(Long userId);   
   List<Expense> findByUser_IdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
