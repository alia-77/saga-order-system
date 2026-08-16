# Saga Order System

A distributed order-processing system built with **Java, Spring Boot, PostgreSQL, Apache Kafka, Docker, Kubernetes, and GitHub Actions**.

The project demonstrates a **Saga-based architecture** for coordinating orders across multiple independent microservices while handling distributed transactions, failures, and compensation.

## Architecture

The system is divided into:

- **Order Service** - manages customer orders.
- **Inventory Service** - manages stock and reservations.
- **Payment Service** - handles payments.
- **Shipping Service** - manages shipment processing.
- **Saga Orchestrator** - coordinates the order workflow and compensation.
- **Shared Module** - contains shared events and models.

## Current Progress

- Multi-module Maven project
- Spring Boot microservices
- REST APIs
- PostgreSQL persistence
- Order creation and retrieval
- Inventory management and reservation
- Kafka-based Order → Inventory communication
- JSON event serialization/deserialization
- Inventory reservation success and failure handling
- Shared event contracts
- Postman API testing
- Environment-based database configuration
- Basic health endpoints
- Docker Compose Kafka setup

## Event Flow

**Current:**

```text
Order Service
      ↓
OrderCreatedEvent
      ↓
Kafka
      ↓
Inventory Service
      ↓
InventoryReservedEvent
      or
InventoryReservationFailedEvent
```

**Planned:**

```text
Inventory
    ↓
Payment
    ↓
Shipping
    ↓
Order Completed
```

Failure paths will use compensating actions to maintain consistency across services.

## Remaining

- Complete Kafka event flows
- Saga orchestration
- Compensation and failure handling
- Payment and shipping integration
- JUnit 5 and Mockito tests
- Testcontainers integration tests
- Dockerize services
- Kubernetes deployment
- GitHub Actions CI/CD
- Monitoring and improved logging
- OpenAPI/Swagger documentation
- Final integration testing and project polish

## Technology Stack

- **Backend:** Java 21, Spring Boot, Maven
- **Database:** PostgreSQL
- **Messaging:** Apache Kafka
- **Testing:** JUnit 5, Mockito, Testcontainers
- **Containers:** Docker, Docker Compose
- **Deployment:** Kubernetes
- **CI/CD:** GitHub Actions
- **API Testing:** Postman
- **API Documentation:** OpenAPI / Swagger

## Project Goal

The goal is to build a realistic distributed backend that demonstrates microservices, event-driven communication, Saga-based distributed transactions, compensating actions, automated testing, containerization, Kubernetes, and CI/CD.