package jorge.cardona.concepts;

import jorge.cardona.concepts.controller.AnimalController;
import jorge.cardona.concepts.controller.FruitController;
import jorge.cardona.concepts.entity.AnimalEntity;
import jorge.cardona.concepts.entity.FruitEntity;
import jorge.cardona.concepts.mappers.AnimalMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootSomeConceptsApplication {

	@Bean
	public AnimalMapper animalMapper(){
		return new AnimalMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSomeConceptsApplication.class, args);
	}


	@Bean
	ApplicationRunner initH2(AnimalController animalController) {
		return args -> {
			Stream.of("Dog", "Cat", "Bird", "Fish").forEach(name -> {
				AnimalEntity animalEntity = AnimalEntity.builder().name(name).build();
				animalController.saveAnimal(animalEntity);
			});
			animalController.getAnimals().forEach(System.out::println);
		};
	}

	@Bean
	ApplicationRunner initEmbeddedMongo(FruitController fruitController) {
		return args -> {
			Stream.of("Banana", "Guama", "Chontaduro", "Coconut").forEach(name -> {
				FruitEntity fruitEntity = FruitEntity.builder().fruitName(name).build();
				fruitController.creatFruit(fruitEntity);
			});
			fruitController.getfruitinfo().forEach(System.out::println);
		};
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