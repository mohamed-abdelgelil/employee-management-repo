FROM  amazoncorretto:11-alpine-jdk as build
WORKDIR /workspace/employee-manage-service
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw install -DskipTests
FROM  amazoncorretto:11-alpine-jdk
ARG JAR_FILE=workspace/employee-manage-service/target/*.jar
COPY --from=build ${JAR_FILE} employee-manage-service.jar
EXPOSE 8080ENTRYPOINT ["java","-jar","/employee-manage-service.jar"]