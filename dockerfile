FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
VOLUME ["/upload"]
ENTRYPOINT ["java","-jar","/app.jar"]