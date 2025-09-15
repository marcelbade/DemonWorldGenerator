FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ./target/demonworld-armygenerator-1.0.jar demonworldGenerator.jar
ENTRYPOINT ["java", "-jar", "/demonworldGenerator.jar"]
