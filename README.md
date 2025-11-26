# Real-Time Order Analytics Platform

A real-time analytics system built using Apache Kafka, Spring Boot microservices,
GraphQL API Gateway, PostgreSQL, and Grafana dashboards.

## Services
- **order-service** → Publishes OrderCreated events to Kafka.
- **analytics-service** → Consumes events, stores metrics in PostgreSQL.
- **graphql-api** → GraphQL endpoint for analytics metrics.
- **grafana** → Real-time dashboards.

## How to Run
```bash
docker-compose up -d
