# Advice API Enhancement

## 📌 Overview
This project is an enhanced Advice API built with Java Spring Boot, designed to provide secure, role-based access to advice entries with full CRUD functionality and scalable architecture.

---

## 🚀 Features Implemented

- **JWT-Based Authentication**  
  Secure login and token-based authorization to protect API endpoints.

- **Role-Based Authorization**  
  Defined two roles: `ADMIN` and `USER`.  
  - `ADMIN` can create, update, and delete advice entries.  
  - `USER` can view advice entries.

- **CRUD Operations for Advice Entity**  
  Full create, read, update, and delete capabilities on advice records.

- **Paginated API Responses**  
  Efficient data retrieval using pagination to handle large datasets gracefully.

- **In-Memory H2 Database**  
  Fast and easy testing and development environment with H2.

- **DTO Mapping and Validation**  
  Clean separation between entity models and API payloads using MapStruct and validation annotations (e.g., `@NotBlank`).

- **Exception Handling**  
  Centralized exception management to return meaningful HTTP responses and error messages.

- **Swagger/OpenAPI Documentation**  
  Comprehensive, interactive API docs with endpoint descriptions, request/response schemas, and examples.

- **Unit and Data Layer Testing**  
  Extensive use of JUnit and Mockito to validate core business logic and repository behavior.

---

## 🗂 Project Structure

-src/main/java/com/example/adviceapi/      
├── config/ # Security & Swagger configuration       
├── controller/ # REST API controllers      
├── dto/ # Request and response DTOs    
├── entity/ # JPA entity models     
├── exception/ # Custom exception handlers    
├── mapper/ # MapStruct mappers for DTO conversion    
├── repository/ # Spring Data JPA repositories    
├── security/ # JWT utilities and filters    
├── service/ # Business logic implementations      
└── AdviceApiApplication.java # Main application entry point



---

## 🔐 Security Details

- **Authentication Flow:**  
  Users authenticate via `/auth/login` endpoint and receive JWT tokens.  
  Tokens are sent in the `Authorization` header for subsequent protected requests.

- **Role Management:**  
  Roles assigned during registration (`USER` by default).  
  An `ADMIN` user is seeded at application startup for management purposes.

- **Access Control:**  
  - `ADMIN` can create, update, delete advice entries.  
  - `USER` can only view advice entries.

---

## 🗂 API Endpoints Overview

| Endpoint            | Method | Description                      | Access           |
|---------------------|--------|--------------------------------|------------------|
| `/auth/register`    | POST   | Register a new user             | Public           |
| `/auth/login`       | POST   | Authenticate user & get JWT    | Public           |
| `/api/advices`      | GET    | Get paginated list of advice   | USER, ADMIN      |
| `/api/advices/{id}` | GET    | Get advice by ID               | USER, ADMIN      |
| `/api/advices`      | POST   | Create new advice              | ADMIN only       |
| `/api/advices/{id}` | PUT    | Update advice by ID            | ADMIN only       |
| `/api/advices/{id}` | DELETE | Delete advice by ID            | ADMIN only       |

---

## 🛠 DTOs and Validation

- Incoming requests are validated using standard Javax Validation annotations like `@NotBlank`.
- MapStruct is used for mapping between entity and DTO, improving maintainability.
- API responses are wrapped in a consistent `Response<T>` format, including success status and message.

---

## 🧪 Testing Strategy

- **Service Layer Tests:**  
  Validating business logic using JUnit and Mockito mocks.

- **Repository Layer Tests:**  
  Mocking data access methods to ensure expected behavior.

- Tests cover successful operations and failure scenarios for robustness.

---

## 📖 Swagger Documentation

- Integrated via Springdoc OpenAPI for live interactive API exploration.
- Accessible at: `http://localhost:8080/swagger-ui.html`
- Includes detailed endpoint summaries, request and response models, and example payloads.

---

## 💡 Design & Implementation Highlights

- **JWT** chosen for stateless, scalable authentication.
- Role checks implemented at controller method level using Spring Security annotations.
- DTO pattern with MapStruct promotes separation of concerns and cleaner code.
- Centralized exception handling enhances API reliability and user feedback.
- Pagination added for improved API usability.
- Comprehensive testing assures code quality and prevents regressions.
- Swagger documentation enriches developer experience and integration.

---

## 📌 Contact

For questions, clarifications, or demos, please contact:

**Mariam Mohammed**  
📧 mariam.mohammed1341@gmail.com


