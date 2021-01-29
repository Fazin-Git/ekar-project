FROM openjdk:8
ADD target/producer-consumer-service.jar producer-consumer-service.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "producer-consumer-service.jar"]