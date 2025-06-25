package com.example.smart_money_manager.controller;

import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @Operation(
        summary = "Get monthly financial report",
        description = "Generates a detailed report of income and expenses for a specific month"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Report generated successfully",
        content = @Content(schema = @Schema(implementation = Map.class))
    )
    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Object>> getMonthlyReport(
            @AuthenticationPrincipal User user,
            @Parameter(description = "Year and month in YYYY-MM format")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(reportService.generateMonthlyReport(user, month));
    }

    @Operation(
        summary = "Trigger monthly report email",
        description = "Sends the monthly report to the user's email immediately"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Email sent successfully"
    )
    @PostMapping("/send-monthly")
    public ResponseEntity<Void> sendMonthlyReportEmail(
            @AuthenticationPrincipal User user) {
        reportService.sendMonthlyReport(user);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Get current month summary",
        description = "Returns a quick summary of the current month's finances"
    )
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getCurrentMonthSummary(
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
            reportService.generateMonthlyReport(user, YearMonth.now())
        );
    }
}
