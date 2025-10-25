# Hospital Management System - Microservices Architecture

This project is a **Hospital Management System** built using **Spring Boot**, **PostgreSQL**, **MongoDB**, **Kafka**, and Docker. The system is designed with a **microservices architecture**, focusing on modularity, scalability, and event-driven communication.

---

## 🚀 Project Overview

The system currently includes the following microservices:

| Service | Description |
|---------|-------------|
| **Laboratory Service** | Manages lab tests, results, and lab-related operations. Uses PostgreSQL for structured data and MongoDB for lab results. |
| **Pharmacy Service** | Handles medications, inventory management, and prescription supervision. Uses PostgreSQL for relational data. |
| **Ordering Service** | Coordinates medication orders and listens for stock-related events via Kafka. Uses MongoDB for order storage. |

Services communicate using **Kafka events** to ensure **loose coupling** and **real-time updates**.

---

## 🏗 Architecture

- **Microservices**: Each service runs independently, encapsulating its own database and business logic.  
- **Databases**:  
  - PostgreSQL: relational storage for structured entities.  
  - MongoDB: document-based storage for lab results and orders.  
- **Event Streaming**: Kafka is used for asynchronous messaging between services.  
- **Containerization**: Docker and Docker Compose orchestrate all services and dependencies.


---

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot (Spring Data JPA, Spring Data MongoDB, Spring Kafka)  
- **Databases**: PostgreSQL 16, MongoDB 7  
- **Messaging**: Apache Kafka, Zookeeper  
- **Containerization**: Docker, Docker Compose  
- **Build & Dependency Management**: Maven  

---

## 🐳 Running the Project with Docker

1. docker compose up -d
