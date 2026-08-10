# Saga Order System

A distributed order-processing system built with **Java, Spring Boot, PostgreSQL, Apache Kafka, Docker, Kubernetes, and GitHub Actions**.

The project demonstrates a **Saga-based architecture** for coordinating an order across multiple independent microservices while handling distributed transactions and failures.

## Architecture

The system is divided into the following services:

* **Order Service** — manages customer orders.
* **Inventory Service** — manages stock and reserves inventory.
* **Payment Service** — processes payments.
* **Shipping Service** — manages shipment preparation.
* **Saga Orchestrator** — coordinates the order workflow.
* **Shared Module** — contains common events, commands, and enums.

## Completed

* Multi-module Maven project structure
* Spring Boot microservices
* REST APIs
* PostgreSQL database for each business service
* Order creation and retrieval
* Inventory management and reservation
* Payment processing
* Shipping management
* Postman API testing
* Environment-based database credentials
* Basic service health endpoint
* Initial project documentation

## Remaining

* Apache Kafka event-driven communication
* Shared Kafka events and commands
* Saga orchestration
* Transaction failure and compensation handling
* JUnit 5 and Mockito tests
* Testcontainers integration tests
* Docker and Docker Compose
* Kubernetes deployment
* GitHub Actions CI/CD
* Improved logging and health monitoring
* Final integration testing and documentation

## Technology Stack

**Backend:** Java 21, Spring Boot, Maven
**Database:** PostgreSQL
**Messaging:** Apache Kafka
**Testing:** JUnit 5, Mockito, Testcontainers
**Containers:** Docker, Docker Compose
**Deployment:** Kubernetes
**CI/CD:** GitHub Actions
**API Testing:** Postman

## Project Goal

The goal is to build a realistic distributed backend system that demonstrates microservices architecture, event-driven communication, distributed transaction management, automated testing, containerization, orchestration, and CI/CD.
