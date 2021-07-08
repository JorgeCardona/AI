FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp

#Add project .jar to app.jar
ADD *.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]