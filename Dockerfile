FROM gradle:9.3.1-jdk21-alpine
WORKDIR /app
COPY  /build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar", "--spring.profiles.active=dev"]