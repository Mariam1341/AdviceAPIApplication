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

- User Register
![3](https://github.com/user-attachments/assets/b0b38d02-27d8-47a3-a6b1-3fa88190e725)

- User Login
![5](https://github.com/user-attachments/assets/e06e49a3-2946-4f95-9328-3945bf1babc9)

- Admin Login
![4](https://github.com/user-attachments/assets/ee11966b-541d-4820-b1c2-e88d223c3149)

- Create Advice (Admin Only Feature)
![t](https://github.com/user-attachments/assets/1d925a5f-073b-49db-89c7-2684637237c2)

- Update Advice (Admin Only Feature)
![update](https://github.com/user-attachments/assets/0fedd3a3-b6e2-4073-85ae-b64ae5dafd10)

- Delete Advice (Admin Only Feature)
![delete](https://github.com/user-attachments/assets/7041d877-152b-408d-8a8a-de5d1abd4352)

- view Advice 
![get](https://github.com/user-attachments/assets/e5d32b1f-cde7-4476-a7e5-8449095c240e)

-view all Advices
![getall](https://github.com/user-attachments/assets/000bc7d0-3f60-4ed8-8993-9a1e5b657115)


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


