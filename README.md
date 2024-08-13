### README.md

````markdown
# Spring Boot MicroService Example

This project demonstrates communication using Kafka with a Spring Boot application. The project includes an `order-service` and a `notification-service`. The `order-service` sends an order message to Kafka, and the `notification-service` receives this message.

## Contents

- [Requirements](#requirements)
- [Setup](#setup)
- [Usage](#usage)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## Requirements

- Docker and Docker Compose
- Java 17 or higher
- Maven

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/sogutemir/BasicSpringMicroServiceProject.git
   cd BasicSpringMicroServiceProject
   ```
````

2. **Open Kafka Port and Allow Firewall Access**

   Ensure that the Kafka port (9092) is open and accessible through the firewall:

   ```bash
   sudo firewall-cmd --zone=public --add-port=9092/tcp --permanent
   sudo firewall-cmd --reload
   ```

3. **Build the Project**

   Use Maven to build the project without running tests:

   ```bash
   mvn clean package -DskipTests
   ```

4. **Start Services with Docker Compose**

   ```bash
   docker-compose up --build
   ```

   This command will start PostgreSQL, Zookeeper, Kafka, order-service, and notification-service.

## Usage

1. **Create an Order with OrderService**

   Send an HTTP POST request to create an order on `order-service`:

   ```bash
   curl -X POST http://localhost:8083/orders -H "Content-Type: application/json" -d '{"userId": 1, "bookId": 1, "quantity": 2, "status": "CREATED"}'
   ```

2. **Receive Notifications with NotificationService**

   The `notification-service` will listen for order messages from Kafka and save them as notifications in the database.

## Configuration

### OrderService

Kafka producer configuration for OrderService is specified in `application.yml`:

```yaml
spring:
  kafka:
    bootstrap-servers: kafka:29092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

### NotificationService

Kafka consumer configuration for NotificationService is specified in `application.yml`:

```yaml
spring:
  kafka:
    bootstrap-servers: kafka:29092
    consumer:
      group-id: notification-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
```

## Troubleshooting

### Kafka Related Errors

- If you encounter `Listener failed` or `DeserializationException` errors, ensure your Kafka configurations are correct.
- Verify that the messages are being sent and received in the correct format.
- Use `docker-compose logs` to check the logs of Docker services and ensure they are running correctly.

### Connection Issues

- Ensure Kafka and Zookeeper services are running correctly using `docker-compose ps`.
- Verify that the service names in the Docker network are configured correctly (e.g., `kafka:29092`).

## Contributing

If you would like to contribute, please submit a pull request or open an issue. Any contributions are appreciated!

## License

This project is licensed under the MIT License. For more information, see the [LICENSE](LICENSE) file.

```

This README file now includes instructions for opening the Kafka port in the firewall and using Maven to build the project without running tests.
```
