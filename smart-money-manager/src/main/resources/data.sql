INSERT INTO users (email, password, role) VALUES 
('user@example.com', '$2a$10$Xl0yhvzLIaJCDdYGBS0L9.QZ.dJ5sX7YwJ6Pc5JZ5Q7tF1JZ5Q7tF', 'USER');

INSERT INTO incomes (source, amount, date, user_id) VALUES
('Salary', 3000.00, '2023-10-01', 1),
('Freelance', 500.00, '2023-10-15', 1);

INSERT INTO expenses (category, amount, date, user_id) VALUES
('Food', 200.00, '2023-10-05', 1),
('Transport', 50.00, '2023-10-10', 1);