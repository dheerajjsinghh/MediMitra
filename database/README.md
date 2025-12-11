# MediMitra Database Setup Guide

## Prerequisites
- MySQL Server 8.0 or higher installed
- MySQL Workbench (optional, for GUI)
- Command line access to MySQL

## Database Setup Instructions

### Step 1: Start MySQL Server
Ensure MySQL server is running on your system.

**Windows:**
```powershell
net start MySQL80
```

**Linux/Mac:**
```bash
sudo systemctl start mysql
# or
sudo service mysql start
```

### Step 2: Login to MySQL
```bash
mysql -u root -p
```
Enter your MySQL root password when prompted.

### Step 3: Create Database and Tables
Execute the schema file:

**From MySQL Command Line:**
```sql
source E:/Projects/MediMitra/database/schema.sql
```

**Or using command line:**
```powershell
mysql -u root -p < E:\Projects\MediMitra\database\schema.sql
```

### Step 4: Insert Sample Data (Optional)
Load sample data for testing:

**From MySQL Command Line:**
```sql
source E:/Projects/MediMitra/database/sample_data.sql
```

**Or using command line:**
```powershell
mysql -u root -p medimitra < E:\Projects\MediMitra\database\sample_data.sql
```

### Step 5: Verify Installation
```sql
USE medimitra;
SHOW TABLES;
SELECT COUNT(*) FROM users;
SELECT COUNT(*) FROM medicines;
```

## Database Configuration

Update the `application.properties` file located at:
`src/main/resources/application.properties`

```properties
# Database Configuration
db.url=jdbc:mysql://localhost:3306/medimitra?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.username=root
db.password=YOUR_MYSQL_PASSWORD
db.driver=com.mysql.cj.jdbc.Driver
```

**Important:** Replace `YOUR_MYSQL_PASSWORD` with your actual MySQL root password.

## Database Schema Overview

### Tables Created:

1. **users** - User accounts (customers, admins, store managers)
2. **categories** - Medicine categories
3. **medicines** - Medicine catalog with pricing and stock
4. **addresses** - User delivery addresses
5. **cart** - Shopping cart items
6. **coupons** - Discount coupons
7. **orders** - Order records
8. **order_items** - Individual items in orders

## Sample Data Overview

### Default Users:
All users have password: `Admin@123` (hashed in database)

| Email | Role | Purpose |
|-------|------|---------|
| admin@medimitra.com | ADMIN | System administrator |
| john.doe@example.com | USER | Sample customer |
| jane.smith@example.com | USER | Sample customer |
| store@medimitra.com | STORE | Store manager |

### Sample Medicines:
- 30+ medicines across 10 categories
- Includes both prescription and non-prescription medicines
- Stock levels and pricing included
- Expiry dates set in future

### Sample Orders:
- 3 pre-populated orders with different statuses
- Order items with pricing
- Addresses linked to users

## Troubleshooting

### Connection Issues:
1. **Error: Access denied for user**
   - Check MySQL username and password in `application.properties`
   - Ensure MySQL user has proper privileges

2. **Error: Unknown database 'medimitra'**
   - Run the schema.sql file first
   - Verify database creation: `SHOW DATABASES;`

3. **Error: Table doesn't exist**
   - Ensure schema.sql executed successfully
   - Check for SQL errors during execution

### Grant Privileges (if needed):
```sql
CREATE USER 'medimitra_user'@'localhost' IDENTIFIED BY 'strong_password';
GRANT ALL PRIVILEGES ON medimitra.* TO 'medimitra_user'@'localhost';
FLUSH PRIVILEGES;
```

Then update `application.properties`:
```properties
db.username=medimitra_user
db.password=strong_password
```

## Backup and Restore

### Backup Database:
```powershell
mysqldump -u root -p medimitra > medimitra_backup.sql
```

### Restore Database:
```powershell
mysql -u root -p medimitra < medimitra_backup.sql
```

## Database Maintenance

### Reset Database:
To start fresh, run:
```sql
DROP DATABASE medimitra;
source E:/Projects/MediMitra/database/schema.sql
source E:/Projects/MediMitra/database/sample_data.sql
```

### Clear Sample Data:
Keep schema but remove sample data:
```sql
USE medimitra;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE order_items;
TRUNCATE TABLE orders;
TRUNCATE TABLE cart;
TRUNCATE TABLE addresses;
TRUNCATE TABLE medicines;
TRUNCATE TABLE categories;
TRUNCATE TABLE coupons;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS = 1;
```

## Next Steps

After database setup:
1. Update `application.properties` with correct credentials
2. Build the project: `mvn clean install`
3. Deploy to Tomcat server
4. Access application at: `http://localhost:8080/medimitra`

## Support

For issues or questions:
- Check application logs in `target/` directory
- Review MySQL error logs
- Verify network connectivity to MySQL server
