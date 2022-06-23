# docker build -t jorgecardona/testkubernetes .
# docker run --rm --name=kubertest -p 9999:9999 jorgecardona/testkubernetes

FROM openjdk:17-alpine

# etiqueta creador de la imagen
MAINTAINER JorgeCardona

EXPOSE 9999

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]