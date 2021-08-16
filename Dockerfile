# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

ARG JAR_FILE=target/product-service-0.0.1-SNAPSHOT.jar

# cp spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar


# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]