# NGOCVT - Spring Boot Management Application

Ứng dụng quản lý được xây dựng bằng Spring Boot 4.0.6 với Java 26, kết nối MySQL database.

**English Version:** [Scroll down to see English version](#english-version)

---

## 📋 Mục Lục
- [Cấu trúc Folder](#cấu-trúc-folder)
- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
- [Cài đặt và Chạy dự án](#cài-đặt-và-chạy-dự-án)
- [Tính năng](#tính-năng)
- [API Endpoints](#api-endpoints)
- [Cấu hình Database](#cấu-hình-database)

---

## 🗂️ Cấu trúc Folder

```
ngocvt/
├── mvnw                           # Maven wrapper (Linux/Mac)
├── mvnw.cmd                       # Maven wrapper (Windows)
├── pom.xml                        # Maven configuration file
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ngocvt/local/ngocvt/
│   │   │       ├── NgocvtApplication.java          # Main application entry point
│   │   │       ├── BaseController.java             # Base controller class
│   │   │       ├── TestController.java             # Test controller
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java         # Spring Security configuration
│   │   │       ├── databases/
│   │   │       │   └── seed.java                   # Database seeding class
│   │   │       ├── helpers/                        # Helper utilities
│   │   │       ├── modules/
│   │   │       │   ├── users/                      # User management module
│   │   │       │   │   ├── controllers/            # User API controllers
│   │   │       │   │   ├── entities/               # User JPA entities
│   │   │       │   │   ├── repositories/           # User repository (DAO)
│   │   │       │   │   ├── services/
│   │   │       │   │   │   ├── interfaces/         # Service interfaces
│   │   │       │   │   │   └── impl/               # Service implementations
│   │   │       │   │   ├── request/                # User request DTOs
│   │   │       │   │   └── resources/              # User response resources
│   │   │       │   └── products/                   # Product management module (similar structure)
│   │   │       ├── repositories/
│   │   │       │   └── BaseRepository.java         # Base repository interface
│   │   │       └── services/
│   │   │           └── BaseService.java            # Base service class
│   │   └── resources/
│   │       ├── application.properties              # Application configuration
│   │       ├── static/                             # Static resources (CSS, JS, images)
│   │       ├── templates/                          # Thymeleaf templates (if using)
│   │       └── db/
│   │           └── migration/
│   │               ├── V202620050840__create_user_catalogue.sql
│   │               └── V202620050841__create_user.sql
│   └── test/
│       └── java/
│           └── ngocvt/local/ngocvt/
│               └── NgocvtApplicationTests.java     # Application tests
└── target/                                         # Build output (auto-generated)
    ├── classes/                                    # Compiled classes
    ├── generated-sources/                          # Generated source files
    └── ngocvt-0.0.1-SNAPSHOT.jar                  # Application JAR file
```

### 📁 Giải thích các thư mục chính:

| Thư mục | Mô tả |
|---------|-------|
| `src/main/java` | Code Java chính của ứng dụng |
| `src/main/resources` | File cấu hình, SQL migrations, static files |
| `src/test` | Unit tests cho ứng dụng |
| `modules` | Các module chức năng (users, products, ...) |
| `services` | Business logic layer |
| `repositories` | Data access layer (DAO) |
| `controllers` | API endpoints |
| `entities` | JPA entity models |
| `request` | Data Transfer Objects (DTOs) cho request |
| `resources` | Data Transfer Objects (DTOs) cho response |
| `db/migration` | Flyway database migration scripts |

---

## 🔧 Yêu cầu hệ thống

- **Java**: JDK 26 trở lên
- **Maven**: 3.8.0 trở lên (hoặc sử dụng Maven wrapper bundled)
- **MySQL**: 5.7 trở lên (hoặc MariaDB 10.3+)
- **IDE**: IntelliJ IDEA, Eclipse, hoặc VS Code + extensions

---

## 🚀 Cài đặt và Chạy dự án

### 1. **Chuẩn bị Database**

```sql
-- Tạo database
CREATE DATABASE test_managment;

-- Cấp quyền cho user
CREATE USER 'root'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON test_managment.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### 2. **Cấu hình Application Properties**

File: `src/main/resources/application.properties`

```properties
spring.application.name=ngocvt
spring.datasource.url=jdbc:mysql://localhost:3306/test_managment
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

**Lưu ý**: Điều chỉnh hostname, port, database name, username, password theo cấu hình của bạn.

### 3. **Chạy ứng dụng**

#### **Cách 1: Sử dụng Maven (Windows PowerShell)**

```powershell
# Từ thư mục ngocvt
cd C:\Users\vu.thi.ngoc\Documents\Learning\ JAVA\02-spring-boot-ngocvt-new\ngocvt

# Compile và chạy
mvn spring-boot:run
```

#### **Cách 2: Sử dụng Maven Wrapper (Windows)**

```cmd
mvnw.cmd spring-boot:run
```

#### **Cách 3: Build JAR và chạy**

```powershell
# Build project
mvn clean package

# Chạy JAR file
java -jar target/ngocvt-0.0.1-SNAPSHOT.jar
```

#### **Cách 4: Sử dụng IDE**

- **IntelliJ IDEA**: 
  - Right-click `NgocvtApplication.java` → Run
  - Hoặc bấm `Shift + F10`

- **Eclipse/STS**: 
  - Right-click project → Run As → Spring Boot App

---

## ✨ Tính năng

### 🔐 **Xác thực & Phân quyền**
- Đăng nhập (Login) với email và password
- Token-based authentication
- Spring Security configuration

### 👥 **Quản lý Người dùng**
- Tạo mới người dùng
- Xem danh sách người dùng
- Cập nhật thông tin người dùng
- Xóa người dùng
- Đăng nhập và lấy token

### 📦 **Quản lý Sản phẩm**
- Tạo sản phẩm mới
- Xem danh sách sản phẩm
- Cập nhật sản phẩm
- Xóa sản phẩm

### 🗄️ **Database Migration**
- Tự động tạo cơ sở dữ liệu khi khởi động
- Flyway migration cho version control của schema
- Initial tables: `user_catalogue`, `user`

---

## 🔌 API Endpoints

### **User Module**

| Method | Endpoint | Mô tả |
|--------|----------|-------|
| POST | `/api/users/login` | Đăng nhập người dùng |
| GET | `/api/users` | Lấy danh sách tất cả người dùng |
| GET | `/api/users/{id}` | Lấy thông tin người dùng theo ID |
| POST | `/api/users` | Tạo người dùng mới |
| PUT | `/api/users/{id}` | Cập nhật người dùng |
| DELETE | `/api/users/{id}` | Xóa người dùng |

### **Ví dụ Request/Response**

#### Đăng nhập:
```json
// Request POST /api/users/login
{
  "email": "haha@gmail.com",
  "password": "password123"
}

// Response
{
  "token": "random token",
  "user": {
    "id": 1,
    "email": "haha@gmail.com"
  }
}
```

---

## ⚙️ Cấu hình Database

### **File Migrations:**

1. **V202620050840__create_user_catalogue.sql**
   - Tạo bảng `user_catalogue` (phân loại người dùng)

2. **V202620050841__create_user.sql**
   - Tạo bảng `user` (thông tin người dùng)

Những file này sẽ tự động được chạy khi ứng dụng khởi động nhờ Flyway.

---

## 📦 Mô tả Dependencies

| Dependency | Phiên bản | Mục đích |
|-----------|-----------|---------|
| Spring Boot Starter Web MVC | 4.0.6 | Xây dựng REST APIs |
| Spring Boot Starter Data JPA | Latest | ORM và database access |
| Spring Security Core | Latest | Bảo mật ứng dụng |
| MySQL Connector Java | Latest | Driver kết nối MySQL |
| Flyway | Latest | Database migration |
| Spring Boot DevTools | Latest | Hot reload (phát triển) |

---

## 🧪 Testing

Chạy unit tests:

```powershell
mvn test
```

Test file: `src/test/java/NgocvtApplicationTests.java`

---

## 🐛 Troubleshooting

### 1. **Error: "Cannot connect to database"**
- Kiểm tra MySQL service đang chạy: `services.msc`
- Kiểm tra cấu hình hostname, port, username, password
- Tạo database `test_managment`

### 2. **Error: "Flyway validation failed"**
- Xóa migrations đã chạy nếu schema changed
- Hoặc reset database: `DROP DATABASE test_managment;`

### 3. **Port 8080 đang bị dùng**
Thêm vào `application.properties`:
```properties
server.port=8081
```

---

## 📝 License

MIT License

---

---

# NGOCVT - Spring Boot Management Application (English Version)

A management application built with Spring Boot 4.0.6 using Java 26, connected to MySQL database.

---

## 📋 Table of Contents
- [Folder Structure](#folder-structure-english)
- [System Requirements](#system-requirements)
- [Installation & Running](#installation--running)
- [Features](#features)
- [API Endpoints](#api-endpoints-english)
- [Database Configuration](#database-configuration)

---

## 🗂️ Folder Structure (English)

```
ngocvt/
├── mvnw                           # Maven wrapper (Linux/Mac)
├── mvnw.cmd                       # Maven wrapper (Windows)
├── pom.xml                        # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ngocvt/local/ngocvt/
│   │   │       ├── NgocvtApplication.java          # Main entry point
│   │   │       ├── BaseController.java             # Base controller
│   │   │       ├── TestController.java             # Test controller
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java         # Security config
│   │   │       ├── databases/
│   │   │       │   └── seed.java                   # Database seeding
│   │   │       ├── helpers/                        # Utilities
│   │   │       ├── modules/
│   │   │       │   ├── users/                      # User module
│   │   │       │   │   ├── controllers/            # Controllers
│   │   │       │   │   ├── entities/               # JPA entities
│   │   │       │   │   ├── repositories/           # DAOs
│   │   │       │   │   ├── services/               # Business logic
│   │   │       │   │   ├── request/                # Request DTOs
│   │   │       │   │   └── resources/              # Response DTOs
│   │   │       │   └── products/                   # Product module
│   │   │       ├── repositories/
│   │   │       │   └── BaseRepository.java         # Base repository
│   │   │       └── services/
│   │   │           └── BaseService.java            # Base service
│   │   └── resources/
│   │       ├── application.properties              # Configuration
│   │       ├── db/migration/                       # SQL migrations
│   │       ├── static/                             # Static files
│   │       └── templates/                          # Templates
│   └── test/
│       └── java/
│           └── NgocvtApplicationTests.java         # Tests
└── target/                                         # Build artifacts
```

---

## 🔧 System Requirements

- **Java**: JDK 26 or higher
- **Maven**: 3.8.0 or higher
- **MySQL**: 5.7 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code

---

## 🚀 Installation & Running

### Step 1: Setup Database

```sql
CREATE DATABASE test_managment;
CREATE USER 'root'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON test_managment.* TO 'root'@'localhost';
```

### Step 2: Configure Application

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test_managment
spring.datasource.username=root
spring.datasource.password=123456
```

### Step 3: Run Application

**Using Maven:**
```powershell
mvn spring-boot:run
```

**Using Maven Wrapper:**
```cmd
mvnw.cmd spring-boot:run
```

**Build & Run JAR:**
```powershell
mvn clean package
java -jar target/ngocvt-0.0.1-SNAPSHOT.jar
```

---

## ⚙️ Default Configuration

- **Server Port**: 8080
- **Database**: MySQL
- **Database Name**: test_managment
- **Default User**: root
- **Default Password**: 123456

---

## 📧 Contact & Support

For issues or questions, please check the project structure and logs.

---

**Last Updated**: May 2026


