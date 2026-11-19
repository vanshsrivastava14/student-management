# Combined Student Management Project

One Maven project for the Student Profile and Skill Development Management System.

## Technology
Java 17+, Spring Boot 3.5.6, Spring Web, Spring JDBC (JdbcTemplate), MySQL, Validation, Postman.

**This project uses JDBC, not Spring Data JPA.** The same MySQL tables are used by the JDBC layer and REST API.

## Setup
1. Keep MySQL Server running.
2. In MySQL Workbench, execute `sql/student_skill_db.sql` if the database/tables do not already exist.
3. Open this folder (the one containing `pom.xml`) in IntelliJ and allow Maven to import dependencies.
4. Edit `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD`.
5. Run `StudentManagementApplication.java`.
6. Import `postman/StudentManagement.postman_collection.json` into Postman.

## APIs
POST `/api/students` — add; GET `/api/students` — all; GET `/api/students/{id}` — by ID;
PUT `/api/students/{id}` — update scores/skills; DELETE `/api/students/{id}` — delete;
GET `/api/students/merit-list` — merit; GET `/api/students/search?email=...` or `?course=...`;
GET `/api/students/same-skills`; GET `/api/students/programming-needs-improvement`.

## Important
The Moodle template labels Q1 as JDBC with MySQL and Q2 as Spring Boot REST API. This one project uses JDBC underneath Spring Boot and provides the REST endpoints too. Confirm with your instructor that one combined code submission is acceptable, since the template does not explicitly say whether separate folders/projects are mandatory.
