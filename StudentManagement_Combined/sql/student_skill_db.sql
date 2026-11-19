CREATE DATABASE IF NOT EXISTS student_skill_db;
USE student_skill_db;
CREATE TABLE IF NOT EXISTS students (
 student_id BIGINT PRIMARY KEY, student_name VARCHAR(100) NOT NULL,
 email VARCHAR(150) NOT NULL UNIQUE, course_name VARCHAR(100) NOT NULL,
 programming_score INT NOT NULL CHECK(programming_score BETWEEN 0 AND 100),
 mathematics_score INT NOT NULL CHECK(mathematics_score BETWEEN 0 AND 100)
);
CREATE TABLE IF NOT EXISTS student_skills (
 student_id BIGINT NOT NULL, skill VARCHAR(100) NOT NULL,
 PRIMARY KEY(student_id,skill),
 FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE
);