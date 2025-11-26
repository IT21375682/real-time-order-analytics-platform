
# Order Analytics Dashboard

## Overview

This project is an **Order Analytics Dashboard** built with **Spring Boot**, **Kafka**, **PostgreSQL**, and **Grafana**. The application tracks and analyzes orders, storing them in a PostgreSQL database. Kafka is used to stream the data between services, while Grafana is employed for real-time monitoring and visualization of key business metrics such as **orders per minute**, **total revenue**, and **total orders**.

## Features

- **Order Service** that produces order events and sends them to Kafka.
- **Analytics Service** that consumes order events from Kafka and stores them in a PostgreSQL database.
- **Grafana Dashboard** that visualizes metrics such as:
  - Orders per minute.
  - Total revenue today.
  - Total orders.
- **Real-time Monitoring** for business metrics.

## Technologies Used

- **Backend**:
  - **Spring Boot** (Java)
  - **Kafka** (for message streaming)
  - **PostgreSQL** (for data storage)
- **Frontend**:
  - **Grafana** (for data visualization)
- **Infrastructure**:
  - **Docker** (for containerization)
  - **Docker Compose** (for multi-container orchestration)

## Architecture

1. **Order Service**:
   - Exposes an endpoint for creating orders (`POST /orders`).
   - Each new order is sent to a Kafka topic (`orders-topic`).
   
2. **Analytics Service**:
   - Consumes messages from the `orders-topic` in Kafka.
   - Processes the order data and stores it in PostgreSQL.

3. **PostgreSQL**:
   - Stores order data and is the source of truth for business metrics.
   
4. **Grafana**:
   - Connects to PostgreSQL to retrieve data and visualize it in real-time dashboards.

## Project Setup

### Prerequisites

Before you begin, ensure you have the following installed:

- **Docker** and **Docker Compose** (for running services in containers).
- **JDK 11+** (for running the Spring Boot applications).
- **Maven** (for building the Spring Boot application).

### 1. **Running the Project Locally**

#### **Step 1: Start the Services using Docker Compose**

Run the following command to start the services:

```bash
docker-compose up -d
```

This will start the following services in the background:

- **PostgreSQL**: Running on port `5432`.
- **Kafka** and **Zookeeper**: Running on ports `9092` and `2181` respectively.
- **Grafana**: Running on port `3000`.

#### **Step 2: Run the Spring Boot Applications**

##### **Order Service**:
- The **Order Service** is a Spring Boot application that listens to requests for creating new orders.
- The service will automatically send order events to **Kafka**.

To run the **Order Service**:

1. Navigate to the **Order Service** directory (`order-service`).
2. Run the application with Maven:

```bash
mvn spring-boot:run
```

##### **Analytics Service**:
- The **Analytics Service** consumes Kafka messages and saves the orders in **PostgreSQL**.

To run the **Analytics Service**:

1. Navigate to the **Analytics Service** directory (`analytics-service`).
2. Run the application with Maven:

```bash
mvn spring-boot:run
```

### 3. **Verify the Setup**

1. **Grafana**: 
   - Open **Grafana** at [http://localhost:3000](http://localhost:3000).
   - Log in with the default credentials:
     - **Username**: `admin`
     - **Password**: `admin`
   - Add the **PostgreSQL** data source and configure it with the following:
     - **Host**: `host.docker.internal:5432`
     - **Database**: `analytics_db`
     - **User**: `analytics`
     - **Password**: `analytics`

2. **Create Orders**:
   - Use a tool like **Postman** or **cURL** to send a `POST` request to `http://localhost:8080/orders` with the following body:

```json
{
  "orderId": "order123",
  "userId": "user1",
  "productId": "product123",
  "quantity": 2,
  "price": 99.99
}
```

3. **Verify Database**:
   - Open a PostgreSQL client (like **pgAdmin** or **psql**) and connect to the database with the credentials:
     - **Host**: `localhost`
     - **Port**: `5432`
     - **Database**: `analytics_db`
     - **User**: `analytics`
     - **Password**: `analytics`
   - Verify that new orders are being inserted into the `order_entity` table.

4. **Verify Grafana Dashboard**:
   - In **Grafana**, you should see real-time visualizations for:
     - **Orders per minute**.
     - **Total revenue**.
     - **Total orders**.

---

## Future Enhancements

- **Add user authentication** to the **Order Service**.
- **Improve Grafana Dashboards** with more advanced visualizations.
- **Add more metrics** to the analytics service, such as `average order value`, `top-selling products`, etc.
- **Use a more robust message queue** like **Kafka Streams** for processing data in real-time.

---
