# MediMitra - Medical E-Commerce Platform

A complete full-stack medical e-commerce web application built with Java, Jakarta EE, MySQL, and Tomcat 11.

## Features

- 🔐 User Authentication (Login, Register, Logout) with BCrypt password hashing
- 💊 Browse Medicines with search and category filters
- 🛒 Shopping Cart with add, update, remove functionality
- 📦 Order Management with order tracking
- 💳 Checkout with multiple payment options (Credit Card, UPI, COD)
- 📍 Address Management for delivery
- 🔒 Role-based access control (Customer, Admin, Pharmacist)
- 📋 Prescription verification for medicines requiring prescriptions
- 📊 Order status tracking (Pending, Confirmed, Processing, Shipped, Delivered, Cancelled)

## Technology Stack

### Backend
- **Java**: JDK 25
- **Jakarta EE 10**: Servlet 6.0, JSP 3.1.1, JSTL 3.0.1
- **Application Server**: Tomcat 11 (via Cargo Maven Plugin)
- **Build Tool**: Maven 4.0.0-rc-3
- **Database**: MySQL 8.0+
- **Connection Pool**: HikariCP
- **Security**: BCrypt for password hashing

### Frontend
- **JSP**: JavaServer Pages with JSTL
- **CSS3**: Modern gradient designs, responsive layout
- **JavaScript**: Vanilla JS for interactivity, AJAX for cart operations

## Project Structure

```
java-maven-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── medimitra/
│   │   │           ├── controller/       # Servlets
│   │   │           ├── dao/              # Data Access Layer
│   │   │           ├── model/            # Domain Models
│   │   │           ├── filters/          # Security Filters
│   │   │           └── util/
│   │   ├── webapp/
│   │   │   ├── WEB-INF/
│   │   │   │   ├── views/                # JSP Pages
│   │   │   │   └── web.xml
│   │   │   ├── assets/
│   │   │   │   ├── css/
│   │   │   │   └── js/
│   │   │   └── index.jsp
│   │   └── resources/
├── sql/
│   ├── schema.sql                        # Database schema
│   └── seed-data.sql                     # Sample data
├── pom.xml
└── README.md
```

## Prerequisites

- **JDK 25**: [Download JDK 25](https://jdk.java.net/25/)
- **Maven 4.0.0-rc-3** or later: [Download Maven](https://maven.apache.org/download.cgi)
- **MySQL 8.0+**: [Download MySQL](https://dev.mysql.com/downloads/mysql/)
- **Tomcat 11**: Automatically handled by Cargo Maven Plugin

## Setup Instructions

### 1. Database Setup

Start MySQL server and create the database:

```bash
# Execute schema
mysql -u root -p < sql/schema.sql

# Load seed data
mysql -u root -p < sql/seed-data.sql
```

### 2. Configure Database Connection

Update database credentials in `src/main/java/com/medimitra/util/DatabaseUtil.java`:

```java
config.setJdbcUrl("jdbc:mysql://localhost:3306/medimitra");
config.setUsername("root");
config.setPassword("your_password");
```

### 3. Build and Run

```bash
# Build the project
mvn clean package

# Run with Tomcat 11
mvn cargo:run
```

Access at: **http://localhost:8080/java-maven-project**

## Default Test Accounts

- **Admin**: admin@medimitra.com / admin123
- **Customer**: john@example.com / password123
- **Pharmacist**: pharmacist@medimitra.com / pharma123

## Key Endpoints

- `/` - Home page
- `/login` - User login
- `/register` - New user registration
- `/medicines` - Browse medicines
- `/cart` - Shopping cart
- `/checkout` - Checkout and place order
- `/orders` - Order history
- `/order-details` - View specific order

## Development

### Build WAR Only
```bash
mvn clean package
```

### Deploy to External Tomcat 11
1. Build: `mvn clean package`
2. Copy `target/java-maven-project.war` to Tomcat's `webapps/`
3. Start Tomcat
4. Access at: http://localhost:8080/java-maven-project/

## Troubleshooting

**Database Connection Issues**
- Verify MySQL is running
- Check credentials in `DatabaseUtil.java`

**Port 8080 Already in Use**
- Stop process on port 8080
- Or change port in `pom.xml`: `<cargo.servlet.port>8081</cargo.servlet.port>`

**Build Failures**
- Ensure JDK 25: `java -version`
- Ensure Maven: `mvn -version`
- Clean cache: `mvn clean`

## License

Educational purposes only.
