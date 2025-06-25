package com.example.smart_money_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartMoneyManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartMoneyManagerApplication.class, args);
    }
}
