-- Run this SQL in your MySQL server to create the database and table
CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

CREATE TABLE IF NOT EXISTS students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  roll VARCHAR(50) NOT NULL,
  department VARCHAR(100),
  email VARCHAR(150)
);

-- Sample data
INSERT INTO students (name, roll, department, email) VALUES
('John Doe','RA2411003001','CSE','john@example.com'),
('Jane Smith','RA2411003002','CSE','jane@example.com');
