# Saga Order System

A distributed order-processing system built with **Java, Spring Boot, PostgreSQL, Apache Kafka, Docker, Kubernetes, and GitHub Actions**.

The project demonstrates a Saga-based architecture for coordinating orders across independent microservices, handling distributed transactions, failures, and compensating actions.

## Architecture

* **Order Service** – manages customer orders
* **Inventory Service** – manages stock and reservations
* **Payment Service** – processes payments
* **Shipping Service** – manages shipment processing
* **Saga Orchestrator** – coordinates the workflow and compensation
* **Shared Module** – shared events and commands

## Event Flow

```text
Order → Inventory Reserved → Payment Completed → Shipment Created
                                    │
                              Payment Failed
                                    │
                            Saga Orchestrator
                                    │
                          Release Inventory Command
                                    │
                            Inventory Released
```

## Current Status

The core Saga workflow is complete and verified end-to-end, both for successful orders and for failed payments with compensation.

Implemented:

* Multi-module Maven project with independent Spring Boot microservices
* REST APIs and PostgreSQL persistence
* Order creation and retrieval
* Inventory management, reservation, and release
* Kafka event-driven communication with JSON serialization
* Shared event and command contracts
* Full Order → Inventory → Payment → Shipping event flow
* Payment success and failure paths
* Saga Orchestrator with payment failure detection and compensation
* Docker Compose Kafka setup and environment-based DB config
* Basic health endpoints
* Postman API testing

## Remaining Work

* Automated unit tests (JUnit 5, Mockito)
* Integration testing with Testcontainers
* More comprehensive end-to-end Saga tests
* Full Docker containerization of all services
* Kubernetes deployment
* GitHub Actions CI/CD
* Monitoring and structured logging
* OpenAPI / Swagger documentation
* Final error handling and project polish

## Tech Stack

Java 21 · Spring Boot · Maven · PostgreSQL · Apache Kafka · JUnit 5 · Mockito · Testcontainers · Docker · Docker Compose · Kubernetes · GitHub Actions · Postman · OpenAPI/Swagger

## Project Structure

```text
saga-order-system/
├── order-service/
├── inventory-service/
├── payment-service/
├── shipping-service/
├── saga-orchestrator/
├── shared/
├── docs/
├── k8s/
├── docker-compose.yml
└── pom.xml
```