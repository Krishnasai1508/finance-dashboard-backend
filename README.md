# Finance Dashboard Backend

## Overview

This project is a backend system for managing financial records with role-based access control and dashboard analytics. It demonstrates API design, data modeling, secure access, and backend architecture.

---

## Tech Stack

* Java
* Spring Boot
* Spring Security (Basic Authentication)
* Spring Data JPA (Hibernate)
* MySQL Database

---

## Features

### User Management

* Create users with roles (ADMIN, ANALYST, VIEWER)
* Manage user status (ACTIVE / INACTIVE)
* Role-based access control

### Financial Records

* Create, view, and delete financial records
* Fields: amount, type (INCOME/EXPENSE), category, date, description
* Filtering by type, category, and date range
* Pagination support

### Dashboard APIs

* Total Income
* Total Expense
* Net Balance
* Category-wise totals
* Recent transactions

### Security

* Basic Authentication (email as username)
* Role-based authorization using `@PreAuthorize`
* Inactive users cannot log in

### Validation & Error Handling

* Input validation using annotations
* Global exception handling
* Proper HTTP status codes (400, 401, 403)

---

## Roles & Permissions

| Role    | Access                        |
| ------- | ----------------------------- |
| ADMIN   | Full access (users + records) |
| ANALYST | View records + dashboard      |
| VIEWER  | View records only             |

---

## API Endpoints

### User APIs

* `POST /users` → Create user (public)
* `GET /users` → Get all users (ADMIN only)

### Record APIs

* `POST /records` → Create record (ADMIN)
* `GET /records` → Get records (All roles, supports filtering & pagination)
* `DELETE /records/{id}` → Delete record (ADMIN)

### Dashboard APIs

* `GET /dashboard/summary`
* `GET /dashboard/category-wise`
* `GET /dashboard/recent`

---

## Running the Application

1. Clone the repository
2. Open the project in your IDE
3. Configure MySQL (see below)
4. Run the Spring Boot application
5. Test APIs using Postman

---

## Database Configuration (MySQL)

Make sure MySQL server is running and create a database:

```sql
CREATE DATABASE financedashboardbackend;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/financedashboardbackend?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

---

## Sample Credentials

Create users using `/users` API:

Admin:

* Email: [admin@test.com](mailto:admin@test.com)
* Password: 123

---

## Sample Request

### Create Financial Record

POST `/records`

```json
{
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-01",
  "description": "Monthly salary"
}
```

---

## Error Handling

* **400 Bad Request** → Validation errors
* **401 Unauthorized** → Authentication required
* **403 Forbidden** → Access denied

---

## Assumptions & Design Decisions

* Basic authentication used for simplicity
* MySQL used for persistent storage
* Filtering supports limited combinations
* Focus is on backend logic, not UI

---

## Testing

* APIs tested using Postman
* Verified:

  * Role-based access control
  * Authentication (401)
  * Authorization (403)
  * Validation errors (400)

---

## Conclusion
  
This project demonstrates backend engineering concepts including secure API design, role-based access control, data processing, and clean architecture suitable for real-world applications.
