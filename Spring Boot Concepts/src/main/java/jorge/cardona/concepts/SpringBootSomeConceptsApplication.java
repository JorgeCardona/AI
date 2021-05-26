package jorge.cardona.concepts;

import jorge.cardona.concepts.controller.AnimalController;
import jorge.cardona.concepts.entity.Animal;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootSomeConceptsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSomeConceptsApplication.class, args);
	}


	@Bean
	ApplicationRunner init(AnimalController animalController) {
		return args -> {
			Stream.of("Dog", "Cat", "Bird", "Fish").forEach(name -> {
				Animal animal = Animal.builder().name(name).build();
				animalController.saveFood(animal);
			});
			animalController.getAnimals().forEach(System.out::println);
		};
	}

}

// Release Version --
// JPA --
// H2 DataBase --
// Actuator --
// Swagger --
// Tareas Automaticas Repetitivas --
// LogBack Json - compress log --
// Sonar --
// context path --
// rest template --
// banner --
// cargar datos de yaml con @ConfigurationProperties(prefix = "yaml") --
// save log every day and compress it --
// cross origin --
// @valid errors --
// Hide URL from Swagger documentation - @Hidden
// HttpServletRequest
// Gitignore
// Profiles
// Mongorepository
// GRAPHQL