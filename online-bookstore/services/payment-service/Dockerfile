FROM openjdk:17-jdk-slim
VOLUME /tmp
ADD target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]
