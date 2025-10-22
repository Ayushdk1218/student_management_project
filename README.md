Student Management System 
============================================
This is a simple CRUD desktop application using Java Swing and MySQL 

Features
- Add, Update, Delete, and View students
- Uses a simple `students` table in MySQL

Requirements
- Java JDK 11+
- MySQL server (or MariaDB)
- MySQL Connector/J JDBC driver (add to classpath)

Database Setup
1. Create a database and table (run the SQL in `db_setup.sql`):
   CREATE DATABASE student_db;
   USE student_db;
   CREATE TABLE students (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     roll VARCHAR(50) NOT NULL,
     department VARCHAR(100),
     email VARCHAR(150)
   );

2. Update database connection settings in `StudentDAO.java` if needed:
   URL, USER, PASS

Compile & Run (command-line)
----------------------------
1. Place mysql-connector-java-x.x.x.jar on the classpath (download from MySQL site).
2. From project folder, compile:
   javac -cp .:/path/to/mysql-connector-java-8.0.33.jar src/*.java -d out
3. Run:
   java -cp out:/path/to/mysql-connector-java-8.0.33.jar Main

Or use an IDE (IntelliJ IDEA / Eclipse): create a project, add the connector jar as a library, then run Main.java.

Notes
- This is a minimal example intended for learning and portfolio submission.
- For production, use connection pooling, input validation, and better error handling.
