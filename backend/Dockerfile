# FROM openjdk:8-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]


FROM openjdk:11

WORKDIR .

COPY target/Spandan.jar .

EXPOSE 8101

CMD ["java", "-jar", "Spandan.jar"]
