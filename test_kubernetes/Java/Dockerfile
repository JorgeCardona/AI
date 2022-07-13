# docker build -t jorgecardona/test-kubernetes-ip-java-17:v1 .
# docker run --rm -d --name=javakubertest -p 9888:9999 jorgecardona/test-kubernetes-ip-java-17:v1
# test la aplicacion en 
# http://localhost:9888
# http://localhost:9888/actuator
# http://localhost:9888/docs -> swagger
# http://localhost:9888/env
# http://localhost:9888/all
# http://localhost:9888/api POST Method -> body {"hostname": "http://localhost","port": 5555}
# http://localhost:9888/web -> GET METHOD http://localhost:9888/web?url=http://localhost:5555/env
# http://localhost:9888/web?url=https://raw.githubusercontent.com/JorgeCardona/skills/master/Python/DB/PeriodicTable.json
# http://localhost:9888/readInfoFromFile -> read data from file
# http://localhost:9888/saveInfoToFile POST->  POST Method -> body {"message": "Jorge Cardona have added Information"}
# http://localhost:9888/createFile -> POST no boby is needed
# http://localhost:9888/deleteFile DELETE -> delete created file
# docker stop javakubertest 



FROM openjdk:17-alpine

# etiqueta creador de la imagen
MAINTAINER JorgeCardona

EXPOSE 9999

ARG JAR_FILE=./app/build/libs/kubernetes-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]