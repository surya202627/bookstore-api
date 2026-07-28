# 📚 Bookstore API

A RESTful API for managing books and authors built with Spring Boot.

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

---

## 📖 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing with Postman](#testing-with-postman)
- [Swagger Documentation](#swagger-documentation)
- [Project Structure](#project-structure)
- [Sample Data](#sample-data)
- [Troubleshooting](#troubleshooting)
- [License](#license)

---

## 🎯 Project Overview

The **Bookstore API** is a RESTful web service built with Spring Boot that provides comprehensive CRUD operations for managing books and authors. It features pagination, filtering, sorting, and is fully documented with Swagger/OpenAPI. This API serves as a robust backend solution for bookstore applications, library management systems, or any application requiring book and author data management.

---

## ✨ Features

### Core Features
- ✅ Full CRUD Operations for Books and Authors
- ✅ Pagination & Sorting support
- ✅ Filtering by genre, price range, author
- ✅ Search functionality
- ✅ Relationship Management (One-to-Many between Authors and Books)

### Technical Features
- ✅ RESTful API design following best practices
- ✅ Swagger/OpenAPI documentation
- ✅ MySQL database with JPA/Hibernate
- ✅ Global Exception Handling
- ✅ Input Validation
- ✅ Lombok for reduced boilerplate code
- ✅ HikariCP connection pooling
- ✅ Postman collection for testing

---

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 3.1.5 | Application Framework |
| Spring Data JPA | - | ORM & Database Access |
| MySQL | 8.0 | Production Database |
| H2 Database | - | Development Database |
| Hibernate | - | JPA Implementation |
| Maven | - | Build & Dependency Management |
| Lombok | - | Boilerplate Code Reduction |
| Swagger/OpenAPI | 2.2.0 | API Documentation |
| Postman | - | API Testing |

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 17 or later
- **Maven** 3.6+
- **MySQL Server** 8.0+ (or Docker)
- **Git** (for cloning)
- **Postman** (for testing, optional)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

### Verify Installation
```bash
java -version
mvn -version
mysql --version
```

---

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/bookstore-api.git
cd bookstore-api
```

### 2. Configure MySQL Database

#### Option A: Using Local MySQL
```sql
-- Connect to MySQL
mysql -u root -p

-- Create database
CREATE DATABASE bookstore_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### Option B: Using Docker (Recommended)
```bash
docker run --name mysql8 \
  -e MYSQL_ROOT_PASSWORD=rootpassword \
  -e MYSQL_DATABASE=bookstore_db \
  -p 3306:3306 \
  -d mysql:8.0
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password_here
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Swagger Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### 4. Build the Project
```bash
mvn clean install
```

---

## 🏃 Running the Application

### Option 1: Using Maven
```bash
mvn spring-boot:run
```

### Option 2: Using JAR file
```bash
mvn package
java -jar target/bookstore-api-1.0.0.jar
```

### Option 3: Using IDE
Run `BookstoreApiApplication.java` as Spring Boot Application

### Application Access Points
- **API Base URL**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/api-docs`

---

## 📡 API Endpoints

### Book Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/books?authorId={id}` | Create a new book |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books` | Get all books (paginated) |
| GET | `/api/books/author/{authorId}` | Get books by author |
| GET | `/api/books/genre/{genre}` | Get books by genre |
| GET | `/api/books/price-range?min={min}&max={max}` | Get books by price range |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

### Author Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/authors` | Create a new author |
| GET | `/api/authors/{id}` | Get author by ID |
| GET | `/api/authors` | Get all authors (paginated) |
| GET | `/api/authors/search?lastName={name}` | Search authors by last name |
| PUT | `/api/authors/{id}` | Update an author |
| DELETE | `/api/authors/{id}` | Delete an author |

### Query Parameters

| Parameter | Description | Example |
|-----------|-------------|---------|
| `page` | Page number (0-indexed) | `?page=0` |
| `size` | Number of items per page | `?size=10` |
| `sort` | Sort field and direction | `?sort=title,asc` |
| `min` | Minimum price | `?min=10` |
| `max` | Maximum price | `?max=20` |
| `lastName` | Last name for author search | `?lastName=Tolkien` |
| `genre` | Book genre | `?genre=Fantasy` |
| `authorId` | Author ID for book creation | `?authorId=1` |

---

## 🧪 Testing with Postman

### Sample Requests

#### Create an Author
```http
POST http://localhost:8080/api/authors
Content-Type: application/json

{
    "firstName": "J.K.",
    "lastName": "Rowling",
    "email": "jk.rowling@email.com",
    "nationality": "British",
    "birthDate": "1965-07-31",
    "biography": "Author of Harry Potter series"
}
```

#### Create a Book
```http
POST http://localhost:8080/api/books?authorId=1
Content-Type: application/json

{
    "title": "Harry Potter and the Sorcerer's Stone",
    "isbn": "9780439708180",
    "price": 19.99,
    "genre": "Fantasy",
    "pages": 309,
    "publicationDate": "1997-06-26",
    "description": "The first novel in the Harry Potter series"
}
```

#### Get All Books (Paginated)
```http
GET http://localhost:8080/api/books?page=0&size=10&sort=price,desc
```

#### Get Books by Price Range
```http
GET http://localhost:8080/api/books/price-range?min=10&max=20
```

### Postman Collection

```json
{
  "info": {
    "name": "Bookstore API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "variable": [
    {
      "key": "baseUrl",
      "value": "http://localhost:8080"
    }
  ]
}
```

---

## 📝 Swagger Documentation

Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

### Swagger Features
- 📖 Interactive API Documentation
- 🎯 Try it out - Test endpoints directly
- 📋 Request/Response Schemas
- 🔍 Search & Filter endpoints
- 📁 Grouped by Controllers

---

## 📁 Project Structure

```
bookstore-api/
├── src/
│   ├── main/
│   │   ├── java/com/bookstore/
│   │   │   ├── BookstoreApiApplication.java
│   │   │   ├── config/
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── BookController.java
│   │   │   │   └── AuthorController.java
│   │   │   ├── service/
│   │   │   │   ├── BookService.java
│   │   │   │   └── AuthorService.java
│   │   │   ├── repository/
│   │   │   │   ├── BookRepository.java
│   │   │   │   └── AuthorRepository.java
│   │   │   ├── entity/
│   │   │   │   ├── Book.java
│   │   │   │   └── Author.java
│   │   │   ├── dto/
│   │   │   │   ├── BookDTO.java
│   │   │   │   └── AuthorDTO.java
│   │   │   └── exception/
│   │   │       ├── ResourceNotFoundException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── com/bookstore/
│               └── BookstoreApiApplicationTests.java
├── pom.xml
├── docker-compose.yml
├── Dockerfile
├── README.md
└── .gitignore
```

---

## 💾 Sample Data

The application comes pre-loaded with:

### Authors (20 sample authors)
- J.K. Rowling
- George R.R. Martin
- J.R.R. Tolkien
- William Shakespeare
- Charles Dickens
- Jane Austen
- Agatha Christie
- Stephen King
- And more...

### Books (54 sample books)
- Harry Potter series (7 books)
- A Song of Ice and Fire series (3 books)
- Lord of the Rings series (4 books)
- Classic literature (15+ books)
- Sci-Fi and Fantasy (10+ books)
- Mystery and Horror (8+ books)

### Genres
- Classic
- Fantasy
- Sci-Fi
- Mystery
- Horror
- Dystopian
- Magical Realism
- Literary Fiction

---

## 🔧 Troubleshooting

### Common Issues & Solutions

#### ❌ Connection Refused
```bash
# Check if MySQL is running
sudo systemctl status mysql   # Linux
brew services list            # Mac
services.msc                  # Windows

# Or use Docker
docker ps | grep mysql
```

#### ❌ Access Denied
```sql
-- Reset MySQL password
ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';
FLUSH PRIVILEGES;
```

#### ❌ Port 8080 Already in Use
```bash
# Find process using port 8080
lsof -i :8080   # Mac/Linux
netstat -ano | findstr :8080   # Windows

# Kill the process
kill -9 <PID>
```

#### ❌ ClassNotFoundException
```bash
# Clean and rebuild
mvn clean install
```

#### ❌ Database Not Found
```sql
-- Create database manually
CREATE DATABASE IF NOT EXISTS bookstore_db;
```

---

## 📄 License

This project is licensed under the MIT License.

```
MIT License

Copyright (c) 2024 Bookstore API

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions...
```

---

## 📞 Contact & Support

### Project Maintainer
- **Name**: Internship Team
- **Email**: support@bookstore-api.com

### Links
- **GitHub**: https://github.com/yourusername/bookstore-api
- **Swagger UI**: http://localhost:8080/swagger-ui.html

---

## 🎯 Quick Start Commands

```bash
# Clone repository
git clone https://github.com/yourusername/bookstore-api.git

# Navigate to project
cd bookstore-api

# Build project
mvn clean install

# Start MySQL (Docker)
docker run --name mysql8 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bookstore_db -p 3306:3306 -d mysql:8.0

# Run application
mvn spring-boot:run

# Access Swagger UI
open http://localhost:8080/swagger-ui.html

# Test API with curl
curl http://localhost:8080/api/authors
```

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [JPA/Hibernate Documentation](https://hibernate.org)
- [MySQL Documentation](https://dev.mysql.com/doc)
- [Swagger/OpenAPI Documentation](https://swagger.io/specification/)
- [Postman Learning Center](https://learning.postman.com)

---

**Happy Coding!** 🚀📚

---
