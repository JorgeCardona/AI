# docker build -t jorgecardona/test-kubernetes-ip-java-17:v1 .
# docker run --rm -d --name=javakubertest -p 9888:9999 jorgecardona/test-kubernetes-ip-java-17:v1
# test la aplicacion en 
# http://localhost:9888
# http://localhost:9888/actuator
# http://localhost:9888/api metodo POST -> body {"hostname": "http://localhost","port": 5555}
# http://localhost:9888/web?url=https://raw.githubusercontent.com/JorgeCardona/skills/master/Python/DB/PeriodicTable.json
# http://localhost:9888/env -> muestra las variables de entorno
# http://localhost:9888/all -> informacion base + variables de entorno

FROM openjdk:17-alpine

# etiqueta creador de la imagen
MAINTAINER JorgeCardona

EXPOSE 9999

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]