# docker build -t jorgecardona/test-kubernetes-ip-java-17:v1 .
# docker run --rm -d --name=javakubertest -p 9888:9999 jorgecardona/test-kubernetes-ip-java-17:v1
# test la aplicacion en 
# http://localhost:9888/java
# http://localhost:9888/java/actuator
# http://localhost:9888/java/docs -> swagger
# http://localhost:9888/java/env
# http://localhost:9888/java/all
# http://localhost:9888/java/api POST Method -> body {"hostname": "http://localhost","port": 5555}
# http://localhost:9888/java/web -> GET METHOD http://localhost:9888/java/web?url=http://localhost:5555/python/env
# http://localhost:9888/java/web?url=https://raw.githubusercontent.com/JorgeCardona/skills/master/Python/DB/PeriodicTable.json
# http://localhost:9888/java/readInfoFromFile -> read data from file
# http://localhost:9888/java/saveInfoToFile POST->  POST Method -> body {"message": "Jorge Cardona have added Information"}
# http://localhost:9888/java/createFile -> POST no boby is needed
# http://localhost:9888/java/deleteFile DELETE -> delete created file
# docker stop javakubertest 



FROM openjdk:17-alpine

# etiqueta creador de la imagen
MAINTAINER JorgeCardona

EXPOSE 9999

ARG JAR_FILE=./app/build/libs/kubernetes-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]