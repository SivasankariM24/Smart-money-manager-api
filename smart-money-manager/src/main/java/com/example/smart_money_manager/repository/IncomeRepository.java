package com.example.smart_money_manager.repository;

import com.example.smart_money_manager.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;


public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUser_Id(Long userId);  
    List<Income> findByUser_IdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
