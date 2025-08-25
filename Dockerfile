FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/patient-api-github-action-aws-ecr-ecs.jar /app
EXPOSE 8080
CMD ["java", "-jar", "patient-api-github-action-aws-ecr-ecs.jar"]