CREATE DATABASE student_db;
USE student_db;
-- student table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    email VARCHAR(100),
    course VARCHAR(50)
);
select * from teachers;
select * from students;
-- user table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL
);



INSERT INTO users (username, password, role) VALUES ('admin', 'admin123','ADMIN');
INSERT INTO users (username, password, role) VALUES ('shubham', 'shubham123','ADMIN');



select * from users;





