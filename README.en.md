# Cookie Shop SSM

A lightweight e-commerce website system developed based on the Spring + SpringMVC + MyBatis (SSM) framework.

## Project Introduction

Cookie Shop is a fully functional online shopping platform built using the classic SSM architecture. The system provides complete product display, shopping cart, order management, user center functionalities, and includes an admin management system for managing products, orders, users, and product categories.

## Technology Stack

- **Backend Framework**: Spring + SpringMVC + MyBatis
- **Database**: MySQL
- **Frontend Templates**: JSP + jQuery + Bootstrap
- **Payment Integration**: Alipay
- **Project Management**: Maven

## Functional Modules

### Frontend Functions
- **User Module**: User registration, login, password modification, shipping address management
- **Product Module**: Product list, product search, product details, hot recommendations, new arrivals
- **Shopping Cart Module**: Add product, modify quantity, delete product
- **Order Module**: Submit order, order list, order details, cancel order
- **Review Module**: Product reviews, ratings

### Backend Management
- **Product Management**: Product CRUD operations, product recommendation settings (carousel, hot sales, new arrivals)
- **Order Management**: Order list, order status modification, order deletion
- **User Management**: User CRUD operations, password reset
- **Category Management**: Product category CRUD operations

## Project Structure

```
cookie-shop-ssm/
├── cookie-shop-common/          # Common Module
│   └── src/main/java/
│       └── io/github/lianjishu/
│           ├── handler/         # Global Exception Handling
│           ├── properties/      # Configuration Properties Class
│           └── utils/           # Utility Classes
├── cookie-shop-pojo/            # Entity Class Module
│   └── src/main/java/
│       └── io/github/lianjishu/entity/
│           ├── Cart.java        # Shopping Cart
│           ├── Goods.java       # Product
│           ├── Order.java       # Order
│           ├── OrderItem.java   # Order Item
│           ├── Review.java      # Review
│           ├── Type.java        # Category
│           ├── User.java        # User
│           ├── Recommend.java   # Recommendation
│           └── Page.java        # Pagination
├── cookie-shop-web/             # Web Module
│   └── src/main/
│       ├── java/
│       │   └── io/github/lianjishu/
│       │       ├── config/      # Configuration Classes
│       │       ├── controller/  # Controllers
│       │       ├── interceptor/ # Interceptors
│       │       ├── mapper/      # MyBatis Mapper Interfaces
│       │       └── service/     # Business Services
│       ├── resources/           # MyBatis Mapping Files
│       ├── webapp/              # Web Resources
│       │   ├── admin/           # Admin Pages
│       │   ├── css/             # Style Sheets
│       │   ├── js/              # JavaScript Files
│       │   └── *.jsp            # JSP Pages
│       └── test/                # Test Classes
└── pom.xml                      # Maven Configuration
```

## Quick Start

### Environment Requirements
- JDK 1.8+
- Maven 3.x
- MySQL 5.7+

### Configuration Steps

1. **Create Database**

```bash
mysql -u root -p < sql/cookieshop.sql
```

2. **Configure Database Connection**

Copy the configuration example file and modify the database connection information:

```bash
cp cookie-shop-web/src/main/resources/jdbc.properties.example \
   cookie-shop-web/src/main/resources/jdbc.properties
```

Edit the `jdbc.properties` file to configure the correct database username and password:

```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/cookieshop?useSSL=false&serverTimezone=UTC
jdbc.username=root
jdbc.password=your_password
```

3. **Configure Alipay (if payment functionality is required)**

Edit `cookie-shop-common/src/main/java/io/github/lianjishu/utils/AlipayConfig.java` to configure Alipay-related parameters:

```java
public static String APPID = "your_app_id";
public static String RSA_PRIVATE_KEY = "your_private_key";
public static String ALIPAY_PUBLIC_KEY = "alipay_public_key";
public static String notify_url = "http://yourdomain/alipay/notify";
public static String return_url = "http://yourdomain/alipay/return";
```

4. **Compile and Run**

```bash
mvn clean package
```

Deploy the generated WAR package to a Tomcat server, or run directly using Maven:

```bash
mvn tomcat7:run -pl cookie-shop-web
```

5. **Access System**

- Frontend Address: http://localhost:8080/
- Backend Address: http://localhost:8080/admin/

## Default Accounts

- **Admin Account**: admin / admin
- **Regular User**: Must register manually

## Project Screenshots

The system includes the following pages:
- Homepage carousel and product display
- Product list page (supports category filtering)
- Product details page
- Shopping cart page
- Order confirmation and submission
- User center
- Admin management system

## License

MIT License