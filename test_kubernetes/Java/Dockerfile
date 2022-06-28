# docker build -t jorgecardona/java-ip-for-test-kubernetes .
# docker run --rm -d --name=javakubertest -p 9888:9999 jorgecardona/java-ip-for-test-kubernetes:latest
# http://localhost:9888
# http://localhost:9888/actuator

FROM openjdk:17-alpine

# etiqueta creador de la imagen
MAINTAINER JorgeCardona

EXPOSE 9999

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]