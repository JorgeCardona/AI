package jorge.cardona.concepts.graphql.configuration;

import jorge.cardona.concepts.graphql.controller.AnimalController;
import jorge.cardona.concepts.graphql.entity.AnimalEntity;
import jorge.cardona.concepts.graphql.mapper.AnimalMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class InitializeBeans {

    @Bean
    public AnimalMapper animalMapper(){
        return new AnimalMapper();
    }

    @Bean
    @Profile("concepts")
    ApplicationRunner initH2Dev(AnimalController animalController) {
        return args -> {
            Stream.of("Racoon", "Dolphin", "Horse", "Canary").forEach(name -> {
                AnimalEntity animalEntity = AnimalEntity.builder().name(name).build();
                animalController.saveAnimal(animalEntity);
            });
            animalController.getAnimals().forEach(System.out::println);
        };
    }

    @Bean
    @Profile("default")
    ApplicationRunner initH2Pdn(AnimalController animalController) {
        return args -> {
            Stream.of("Dog", "Cat", "Bird", "Fish").forEach(name -> {
                AnimalEntity animalEntity = AnimalEntity.builder().name(name).build();
                animalController.saveAnimal(animalEntity);
            });
            animalController.getAnimals().forEach(System.out::println);
        };
    }
}
