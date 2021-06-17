package jorge.cardona.concepts;

import jorge.cardona.concepts.order.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSomeConceptsApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootSomeConceptsApplication.class, args);
	}

	@Autowired
	private Numbers positions;

	@Override
	public void run(String... strings) throws Exception {
		positions.printPosition();
	}

}

// Release Version --
// final for constants
// JPA --
// H2 DataBase --
// Actuator --
// Swagger --
// Tareas Automaticas Repetitivas --
// LogBack Json - compress log --
// dto - entity
// Sonar --
// context path --
// rest template --
// banner --
// cargar datos de yaml con @ConfigurationProperties(prefix = "yaml") --
// save log every day and compress it --
// cross origin --
// @valid errors --
// Hide URL from Swagger documentation - @Hidden
// multiples examples swagger, parameters, headers
// add SecurityScheme Authorization Header
// HttpServletRequest
// optimize response JSON of service
// {data:{},
// status: message,
// code: 200}
// Gitignore
// Profiles
// Mongorepository---
// GRAPHQL---
// anotaciones propias