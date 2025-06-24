# 🚀 User Management API

Dự án xây dựng hệ thống RESTful API sử dụng Spring Boot, hỗ trợ xác thực và phân quyền bằng JWT. Bao gồm các chức năng đăng ký, đăng nhập, quản lý người dùng, và bảo vệ các endpoint theo quyền truy cập (`USER`, `ADMIN`).

---

## 🧰 Môi trường yêu cầu
-- Java 21
-- JDK 21
-- Spring boot 3
-- Database: My SQL

---

## Các dependencies sử dụng
-- Spring Web
-- Spring Security
-- Spring JPA
-- MySql Connector
-- Lombok
-- Jsonwebtoken
-- Springdoc

---

## ⚙️ Cài đặt & cấu hình

### 1. Clone source code

git clone https://github.com/SangToVan/user_management.git
cd user_management

### 2. Cấu hình CSDL

-- Truy cập file application.properties và nhập thông tin theo hướng dẫn dưới đây

spring.datasource.url=jdbc:mysql://localhost:3307/user_manage (đường dẫn đến csdl)
spring.datasource.username=root (nhập username và password)
spring.datasource.password=root

### 3. Tạo key

app.jwt.secret-key= (yêu cầu key có 128 ký tự)

## Khởi chạy ứng dụng
-- Cách 1: Sử dụng Maven

mvn spring-boot:run

-- Cách 2: Sử dụng IDE

Mở class UserManagementApplication.java chứa main()
Click Run

-- Sau khi build và chạy thành công, truy cập đường link dưới đây để kiểm tra các API
http://localhost:8080/swagger-ui/index.html 

-- Sử dụng tài khoản admin để quản lý user
email: admin@admin.com
pass: admin123

