# MiniIMS Spring Boot Application

## 📝 Overview
MiniIMS is a Spring Boot application built with Java 17 and MySQL. It provides a secure REST API with JWT authentication.

## 🚀 Features
- ✅ JWT-based authentication  
- ✅ RESTful APIs for managing data  
- ✅ MySQL database integration  
- ✅ CORS support for frontend apps  
- ✅ Swagger UI for interactive API documentation  

## 🛠️ Tech Stack
- Java 17  
- Spring Boot  
- MySQL  
- Maven  
- JWT  
- Swagger/OpenAPI  

## ⚙️ Setup Instructions

### Prerequisites
- Java 17
- MySQL  
- Maven  
- `.env` file (explained below)

### 1. Clone the Repository
then
cd miniims
```

### 2. Create the Database

Create a MySQL database named `miniims`:

```sql
CREATE DATABASE miniims;
```

### 3. Import Initial Data (Optional)

You can import the SQL file sent via email using:

- **phpMyAdmin** → Use the *Import* tab  
- **MySQL Workbench** → File → Run SQL Script

Or skip this and create your data manually through the frontend.

### 4. Create a `.env` File

At the root of the project, create a `.env` file with the following:

```.env
JWT_SECRET=your_jwt_secret_key
CORS_ALLOWED_ORIGIN=https://your-frontend-url.com
```

### 5. Update Database Credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/miniims
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

### 6. Run the Application

```bash
./mvnw spring-boot:run
or via run button
```

---

## 🌐 Access the Application

- API Base URL: `http://localhost:8081`  
- Swagger UI: http://localhost:8081/swagger-ui/index.html

---

## 📦 Usage

- The react project includes its own README file with setup and usage instructions.
    or
- Test and explore APIs via Swagger UI.
