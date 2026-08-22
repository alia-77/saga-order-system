# Saga Order System

A distributed order-processing system built with **Java, Spring Boot, PostgreSQL, Apache Kafka, Docker, Kubernetes, and GitHub Actions**.

The project demonstrates a **Saga-based architecture**: coordinating orders across independent microservices, and handling distributed transactions, failures, and compensating actions between them.

## Goal

Build a realistic distributed backend demonstrating microservices architecture, event-driven communication via Kafka, Saga-based distributed transactions with compensation, automated testing, containerization, Kubernetes orchestration, and CI/CD.

The end result should be a complete, testable order-processing workflow where independent services communicate through events, and failures are handled through Saga-based compensation rather than left inconsistent.

## Architecture

- **Order Service** - manages customer orders
- **Inventory Service** - manages stock and reservations
- **Payment Service** - processes payments
- **Shipping Service** - manages shipment processing
- **Saga Orchestrator** - coordinates the order workflow and compensation
- **Shared Module** - shared events and models

## Event Flow

**Working today:**

```text
Order Service -> OrderCreatedEvent -> Kafka
Kafka -> Inventory Service -> InventoryReservedEvent -> Kafka
Kafka -> Payment Service -> PaymentCompletedEvent -> Kafka
```

Inventory failures are also represented through a separate path:

```text
Inventory Service -> InventoryReservationFailedEvent -> Kafka
```

Payment is currently simulated; a full success/failure mechanism is part of the Saga workflow, still to be built.

**Planned full flow:**

```text
Order -> Inventory -> Payment -> Shipping -> Order Completed
```

Failures will be handled through compensating actions coordinated by the Saga Orchestrator, for example:

```text
Inventory Reserved -> Payment Failed -> Saga Orchestrator -> Release Inventory
```

## Current Status

- Multi-module Maven project with independent Spring Boot microservices, REST APIs, and PostgreSQL persistence
- Order creation/retrieval and inventory reservation implemented
- Kafka-based communication between Order and Inventory, with shared event contracts (`OrderCreatedEvent`, `InventoryReservedEvent`, `InventoryReservationFailedEvent`, `PaymentCompletedEvent`)
- Simulated payment processing, triggered by inventory reservation and publishing back to Kafka
- Docker Compose Kafka setup, environment-based config, basic health endpoints, Postman-tested APIs

## Remaining Work

- Payment failure path (`PaymentFailedEvent`, simulated failures, wiring into the Saga)
- Shipping event flow (consume `PaymentCompletedEvent`, publish shipment results)
- Saga Orchestrator (coordinate the full workflow, track progress, trigger next steps)
- Compensation logic for rollback scenarios (e.g. release inventory on payment failure)
- Automated testing (JUnit 5, Mockito, Testcontainers, end-to-end Saga tests)
- Full containerization and Kubernetes deployment
- CI/CD via GitHub Actions
- Monitoring, structured logging, and OpenAPI/Swagger documentation

## Tech Stack

- **Backend:** Java 21, Spring Boot, Maven
- **Database:** PostgreSQL
- **Messaging:** Apache Kafka
- **Testing:** JUnit 5, Mockito, Testcontainers
- **Containers:** Docker, Docker Compose
- **Deployment:** Kubernetes
- **CI/CD:** GitHub Actions
- **API Testing:** Postman
- **API Documentation:** OpenAPI / Swagger