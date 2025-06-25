package com.example.smart_money_manager.service;

import com.example.smart_money_manager.model.Expense;
import com.example.smart_money_manager.model.Income;
import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.repository.ExpenseRepository;
import com.example.smart_money_manager.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final EmailService emailService;

    public Map<String, Object> generateMonthlyReport(User user, YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Income> incomes = incomeRepository.findByUser_IdAndDateBetween(
                user.getId(), startDate, endDate);
        List<Expense> expenses = expenseRepository.findByUser_IdAndDateBetween(
                user.getId(), startDate, endDate);

        double totalIncome = incomes.stream()
                .mapToDouble(Income::getAmount)
                .sum();

        double totalExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> expenseByCategory = new HashMap<>();
        for (Expense expense : expenses) {
            expenseByCategory.merge(
                    expense.getCategory(),
                    expense.getAmount(),
                    Double::sum
            );
        }

        Map<String, Object> report = new HashMap<>();
        report.put("period", yearMonth.toString());
        report.put("totalIncome", totalIncome);
        report.put("totalExpense", totalExpense);
        report.put("savings", totalIncome - totalExpense);
        report.put("expenseByCategory", expenseByCategory);
        report.put("incomes", incomes);
        report.put("expenses", expenses);

        return report;
    }

    public void sendMonthlyReport(User user) {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        Map<String, Object> report = generateMonthlyReport(user, lastMonth);

        @SuppressWarnings("unchecked")
        Map<String, Double> expenseByCategory = (Map<String, Double>) report.get("expenseByCategory");

        String reportText = String.format(
                "Monthly Financial Report (%s)%n" +
                        "==========================%n" +
                        "Income   : $%.2f%n" +
                        "Expenses : $%.2f%n" +
                        "Savings  : $%.2f%n%n" +
                        "Expense Breakdown:%n%s",
                report.get("period"),
                report.get("totalIncome"),
                report.get("totalExpense"),
                report.get("savings"),
                formatExpenseCategories(expenseByCategory)
        );

        emailService.sendMail(
                user.getEmail(),
                "Monthly Financial Report - " + lastMonth,
                reportText
        );
    }

    private String formatExpenseCategories(Map<String, Double> categories) {
        StringBuilder sb = new StringBuilder();
        categories.forEach((category, amount) ->
                sb.append(String.format("- %s: $%.2f%n", category, amount))
        );
        return sb.toString();
    }
}
