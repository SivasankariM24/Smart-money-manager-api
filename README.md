# Smart Money Manager API

## Overview

Smart Money Manager is a Spring Boot application that provides a backend API for managing personal finances. It allows users to track their income, expenses, and generate financial reports.

---

## Table of Contents

- [Technologies](#technologies)
- [Installation](#installation)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [File Structure](#file-structure)
- [Configuration](#configuration)
- [Database](#database)
- [How to Contribute](#how-to-contribute)

---

## Technologies

- Java 21
- Spring Boot 3.x
- Spring Security
- JPA / Hibernate
- MySQL or PostgreSQL (check `application.yml`)
- Maven

---

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/SivasankariM24/smart-money-manager-api.git
   ```

2. **Navigate into the project directory**
   ```bash
   cd smart-money-manager-api
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

---

## Features

- User Authentication
- Income and Expense Tracking
- Financial Reporting
- JWT for secure authentication

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login existing user |
| GET | `/api/income` | Get all income entries |
| POST | `/api/income` | Add new income |
| DELETE | `/api/income/{id}` | Delete income entry |
| GET | `/api/expense` | Get all expense entries |
| POST | `/api/expense` | Add new expense |
| DELETE | `/api/expense/{id}` | Delete expense entry |
| GET | `/api/report` | Generate financial report |

---

## File Structure

```
smart-money-manager-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── smart_money_manager/
│   │   │               ├── config/
│   │   │               │   ├── JwtAuthFilter.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── ExpenseController.java
│   │   │               │   ├── IncomeController.java
│   │   │               │   └── ReportController.java
│   │   │               ├── dto/
│   │   │               │   ├── AuthResponse.java
│   │   │               │   ├── LoginRequest.java
│   │   │               │   └── RegisterRequest.java
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               └── service/
│   │   │                   ├── AuthService.java
│   │   │                   ├── EmailService.java
│   │   │                   ├── ExpenseService.java
│   │   │                   ├── IncomeService.java
│   │   │                   └── ReportService.java
│   │   ├── resources/
│   │   │   ├── application.yml
│   │   │   ├── data.sql
│   │   │   └── schema.sql
│   └── test/
├── .mvn/
├── mvnw
└── pom.xml
```

---

## Configuration

Update the `application.yml` file to configure database connections and other environment settings.

---

## Database

The project uses SQL files (`schema.sql` and `data.sql`) located in the `resources` folder to set up the database structure and initial data.

---

## How to Contribute

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a Pull Request.
