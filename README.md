# Employees-managment-system
This project is a microservices-based Employee Management System built with Spring Boot. It provides functionalities for managing employee, including creating, updating, deleting, and viewing employee details. It also includes features like authentication, report generation, and Notification service.

# Features
- User Authentication service (login/logout functionality).
- Employee Management service (add, edit, delete, view employee details).
- Notification service (send a confirm notification while an epmloyee has added).
- Report Generation service (Generate employee reports).

# Installation
1- Clone the repository:
git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system

2- Set up MySQL and create a database: 
CREATE DATABASE spring_db;

3- Configure application.properties with your database details.

4- Build and run the project:
mvn clean install
mvn spring-boot:run

5- Visit http://localhost:8080 in your browser
