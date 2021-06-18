# Enviroment Variables
```
ACTUATOR_ENV_VARIABLE_TEST=CONTENT TEST VALUE
SPRING_PROFILES_ACTIVE=concepts
```

# Install Sonar
- Install Sonar in Docker
  * docker pull sonarqube
- Start the server by running:
  * docker run -d --name sonarqube_concepts -p 9000:9000 sonarqube
- Log in to http://localhost:9000 with System Administrator credentials (login=admin, password=admin).
- Click the Create new project button to analyze your first project.

# Publish Project in Sonarqube
- gradle sonarqube

# Sonar Metrics

[![Quality Gate Status](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=alert_status)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Bugs](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=bugs)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Reliability Rating](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=reliability_rating)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Vulnerabilities](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=vulnerabilities)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Security Rating](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=security_rating)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)


[![Technical Debt](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=sqale_index)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Code Smells](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=code_smells)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Maintainability Rating](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=sqale_rating)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)

[![Coverage](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=coverage)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Duplicated Lines (%)](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=duplicated_lines_density)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)
[![Lines of Code](http://localhost:9000/api/project_badges/measure?project=org.sonarqube%3Ajava-gradle-simple&metric=ncloc)](http://localhost:9000/dashboard?id=org.sonarqube%3Ajava-gradle-simple)

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Boot Releases](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot)
* [Spring Cloud Releases](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies)
* [Spring Anotations 1](https://springframework.guru/spring-framework-annotations/)
* [Spring Anotations 2](https://www.baeldung.com/spring-core-annotations)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data](https://spring.io/projects/spring-data)
* [Spring Security](https://spring.io/projects/spring-security#learn)

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.10.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.10.RELEASE/gradle-plugin/reference/html/#build-image)

* [Markdown Documentation](https://www.markdownguide.org/basic-syntax/)
* [Swagger Security](https://swagger.io/docs/specification/authentication/)
* [Spring Actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator)
* [Lombok](https://projectlombok.org/features/all)
* [CommandLineRunner & ApplicationRunner](https://www.javacodegeeks.com/2019/09/spring-boot-commandlinerunner-and-applicationrunner.html)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
  <br>
  https://www.tutorialspoint.com/hibernate/hibernate_mapping_types.htm

