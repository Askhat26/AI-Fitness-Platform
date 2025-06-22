# 🧠 SmartFitness AI Microservices Platform

A modular AI-based fitness backend platform built with **Spring Boot**, supporting **user authentication**, **user profile management**, **AI workout & nutrition suggestions**, **real-time notifications**, and **asynchronous event-driven communication** using **RabbitMQ** and **Resilience4j Circuit Breakers**.

---

## ⚙️ Microservices Overview

| Service             | Port  | Description                                                |
|---------------------|-------|------------------------------------------------------------|
| Auth Service        | 8084  | User registration, login, and JWT token handling          |
| User Service        | 8081  | Basic user data service                                   |
| AI Service          | 8083  | ML-powered fitness recommendations (calls Python ML)      |
| User Profile        | 8085  | Stores user profile info + emits events via RabbitMQ      |
| Notification Service| 8086  | Listens to RabbitMQ profile events, sends notifications   |
| Activity Service    | 8082  | User Activity                   |
| Eureka Server       | 8761  | Service discovery                                          |
| RabbitMQ            | 5672  | Message broker for communication between services         |

---

## 🧱 Tech Stack

- **Java 17+**, **Spring Boot 3**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud OpenFeign**
- **Spring Data JPA + PostgreSQL**
- **RabbitMQ (Spring AMQP)**
- **Resilience4j (Circuit Breaker + Retry)**
- **Feign Client Communication**


---

## ✅ Key Features

### 🔐 Auth Service
- User registration & login
- JWT token generation & validation

### 👤 User Profile Service
- Create, retrieve, and update profile info
- Publishes profile update events to RabbitMQ (`profile.exchange`)
- Uses Feign + Circuit Breaker to validate auth tokens

### 📬 Notification Service (NEW)
- Listens to RabbitMQ `profile.updated` events
- Sends notifications (email, SMS, etc.) on profile update
- Future-proofed for scaling with more event types

### 🤖 AI Service
- Simulates fitness progress, suggests workouts or diets
- Receives requests from profile service
- Calls Python ML model (via REST) for predictions

### 🔁 Resilience & Reliability
- **Resilience4j** protects services from:
  - Auth or AI service downtime
  - Transient errors
- Retry, fallback & circuit breaker enabled for Feign calls

---

📂 Folder Structure
  smartfitness/
│
├── auth_service/
├── activity-service
├── user_service/
├── user_profile_service/  
├── ai_service/
│   └── (Java + Python ML connector)
├── notification_service/
│   ├── config/
│   └── consumer/
├── eureka-server/

