FROM openjdk:17.0.1
ARG JAR_FILE=target/student-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]