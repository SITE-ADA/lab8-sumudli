# University Management System

## Project Overview

This project is a microservice-based University Management System developed using Spring Boot.

The system supports:

- Student management
- Course management
- Student enrollment
- Enrollment date tracking
- Prerequisite course validation
- Getting courses by student name
- Swagger API documentation

The project contains two microservices:

1. student-service
2. course-service

---

# Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Web
- PostgreSQL
- OpenFeign
- Lombok
- Swagger / OpenAPI
- Gradle

---

# Project Structure

```text
university-system
│
├── student-service
│
├── course-service
│
└── README.md
Database Setup

Open pgAdmin and create two databases:

studentdb
coursedb

Example PostgreSQL configuration:

spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

For course-service:

spring.datasource.url=jdbc:postgresql://localhost:5432/coursedb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
How To Run The Project

Open terminal in project root.

Run student-service
.\gradlew.bat :student-service:bootRun
Run course-service
.\gradlew.bat :course-service:bootRun
Swagger URLs
Student Service Swagger

http://localhost:9090/swagger-ui/index.html

Course Service Swagger

http://localhost:8081/swagger-ui/index.html

Features
1. Student Management

Supports:

create student
update student
delete student
get student by id
get all students
2. Course Management

Supports:

create course
update course
delete course
get course by id
get all courses
3. Enrollment Date

When a student enrolls into a course, the system stores:

student id
course id
enrollment date

Example response:

{
  "enrollmentId": 1,
  "courseId": 1,
  "studentId": 1,
  "enrollmentDate": "2026-05-20",
  "message": "Student enrolled successfully."
}
4. Prerequisite Validation

Courses may contain prerequisiteCourseId.

Example:

{
  "title": "Algorithms",
  "code": "CS301",
  "credits": 4,
  "prerequisiteCourseId": 1
}

Before enrollment, the system validates prerequisite completion.

If prerequisite is missing:

{
  "message": "Student must complete prerequisite course first."
}
5. Get Courses By Student Name

Endpoint:

GET /api/v1/courses/student-name?name=Nicat

Example response:

[
  {
    "id": 1,
    "title": "Programming Fundamentals",
    "code": "CS101",
    "credits": 5,
    "prerequisiteCourseId": null
  }
]
Example Requests
Create Student
POST /api/v1/students
{
  "firstName": "Nicat",
  "lastName": "Aliyev",
  "email": "nicat@example.com",
  "age": 20
}
Create Course
POST /api/v1/courses
{
  "title": "Programming Fundamentals",
  "code": "CS101",
  "credits": 5
}
Enroll Student
POST /api/v1/courses/1/students/1
Important Notes
PostgreSQL must be running before starting services.
student-service must run before course-service.
Swagger is used for endpoint testing.
Feign Client is used for inter-service communication.
Course codes must be unique.
Prerequisite course id is optional and may be null.