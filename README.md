# 🏠 StayEase

A full-stack Spring Boot application that replicates the core functionality of Airbnb, a peer-to-peer online marketplace for booking accommodations.

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [Project Structure](#-project-structure)
- [API Documentation](#-api-documentation)
- [Security](#-security)
- [Database](#-database)
- [Testing](#-testing)
- [Contributing](#-contributing)
- [License](#-license)

## 🎯 Overview

This project is StayEase, built with modern Java technologies. It demonstrates a comprehensive understanding of enterprise-level application development, including REST API design, database management, user authentication, and payment processing.

The application allows users to:
- Register and manage their accounts
- List properties for rent
- Search and book accommodations
- Process payments securely
- Manage bookings and reviews

## ✨ Features

### User Management
- User registration and authentication
- JWT-based token authentication
- Role-based access control (RBAC)
- User profile management

### Property Management
- Create and manage property listings
- Property search and filtering
- Property availability management
- Property reviews and ratings

### Booking System
- Search available properties
- Book accommodations
- Manage reservations
- Cancel bookings

### Payment Processing
- Secure payment integration with Stripe
- Transaction management
- Payment history tracking

### API Documentation
- OpenAPI/Swagger integration
- Auto-generated API documentation
- Interactive API testing

## 🛠️ Tech Stack

### Backend Framework
- **Java 21** - Programming language
- **Spring Boot 4.0.2** - Web framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Object-relational mapping and database access

### Database
- **PostgreSQL** - Relational database

### Authentication & Security
- **JWT (JSON Web Tokens)** - Token-based authentication
- **Spring Security** - Security framework
- **jjwt 0.12.6** - JWT library for Java

### Payment Integration
- **Stripe API** - Payment processing

### Additional Libraries
- **Lombok** - Reduces boilerplate code with annotations
- **ModelMapper** - Simplifies object mapping
- **SpringDoc OpenAPI** - Swagger/OpenAPI documentation

### Build Tool
- **Maven** - Dependency management and project build

## 📦 Prerequisites

Before you begin, ensure you have the following installed:
- **Java 21** or higher
- **Maven 3.6** or higher
- **PostgreSQL 12** or higher
- **Git**

### Optional
- **Docker** - For containerized deployment

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/0harshitsingh1/AirBnb_Project.git
cd AirBnb_Project
```

### 2. Configure Database

Create a PostgreSQL database:

```sql
CREATE DATABASE airbnb_db;
```

### 3. Set Environment Variables

Create an `application.properties` or `application.yml` file in `src/main/resources/` and configure:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/airbnb_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Configuration
jwt.secret=your_secret_key_here
jwt.expiration=86400000

# Stripe Configuration
stripe.api.key=your_stripe_api_key

# Logging
logging.level.root=INFO
```

## ⚙️ Configuration

### JWT Secret Key
Replace `your_secret_key_here` with a strong, randomly generated secret key.

### Stripe API Key
Register at [Stripe](https://stripe.com) and add your API key to the configuration.

### Database Connection
Ensure PostgreSQL is running and accessible at the configured URL.

## 🏃 Running the Application

### Using Maven

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Using Maven Wrapper (Windows)

```bash
mvnw.cmd spring-boot:run
```

### Using Maven Wrapper (Linux/Mac)

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📁 Project Structure

```
AirBnb_Project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/AirBnb/projects/
│   │   │       ├── controller/        # REST API endpoints
│   │   │       ├── service/           # Business logic
│   │   │       ├── repository/        # Data access layer
│   │   │       ├── model/             # Entity classes
│   │   │       ├── dto/               # Data transfer objects
│   │   │       ├── security/          # Security configuration
│   │   │       └── exception/         # Custom exceptions
│   │   └── resources/
│   │       ├── application.properties # Configuration
│   │       └── db/                    # Database initialization scripts
│   └── test/
│       └── java/                      # Unit and integration tests
├── pom.xml                             # Maven dependencies
└── README.md                           # This file
```

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api-docs`

The API documentation provides:
- All available endpoints
- Request/response schemas
- Example payloads
- Interactive testing interface

## 🔐 Security

### Authentication Flow

1. User registers with email and password
2. User logs in to receive JWT token
3. JWT token is included in request headers for authenticated endpoints
4. Spring Security validates token and enforces role-based access control

### Password Security
- Passwords are encrypted using Spring Security's BCryptPasswordEncoder
- Never transmitted or logged in plain text

### CORS Configuration
Configure CORS settings in the security configuration class to specify allowed origins, methods, and headers.

## 🗄️ Database

### Schema Management
- Hibernate auto-creates/updates tables based on entity annotations
- Use `spring.jpa.hibernate.ddl-auto=update` for development

### Key Entities
- **User** - Application users (hosts and guests)
- **Property** - Rental properties
- **Booking** - Property reservations
- **Review** - User reviews and ratings
- **Payment** - Transaction records

### Entity Relationships
- One User can have many Properties
- One Property can have many Bookings
- One Booking can have one Payment
- Users can leave multiple Reviews

## 🧪 Testing

Run tests using Maven:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ClassName

# Run tests with coverage
mvn test jacoco:report
```

### Test Coverage
- Unit tests for service layer logic
- Integration tests for repository operations
- Controller tests for API endpoints

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards
- Follow Google Java Style Guide
- Write meaningful commit messages
- Add tests for new features
- Update documentation as needed

## 📝 License

This project is open source and available under the MIT License. See the LICENSE file for more details.

---

## 🆘 Troubleshooting

### Database Connection Issues
- Ensure PostgreSQL is running
- Check database credentials in configuration
- Verify database exists and user has proper permissions

### Maven Build Failures
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`
- Check Java version compatibility

### Port Already in Use
- Change server port in `application.properties`
- Kill process using port 8080: `lsof -ti:8080 | xargs kill -9`

## 📞 Support

For questions or issues:
- Create a GitHub issue
- Check existing issues for solutions
- Review API documentation

---

**Happy Coding! 🚀**
