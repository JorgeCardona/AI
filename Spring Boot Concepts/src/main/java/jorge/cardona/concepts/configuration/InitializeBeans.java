package jorge.cardona.concepts.configuration;

import jorge.cardona.concepts.controller.AnimalController;
import jorge.cardona.concepts.controller.FruitController;
import jorge.cardona.concepts.entity.AnimalEntity;
import jorge.cardona.concepts.entity.FruitEntity;
import jorge.cardona.concepts.mappers.AnimalMapper;
import jorge.cardona.concepts.order.Numbers;
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
    ApplicationRunner initEmbeddedMongo(FruitController fruitController) {
        return args -> {
            Stream.of("Banana", "Guama", "Chontaduro", "Coconut").forEach(name -> {
                FruitEntity fruitEntity = FruitEntity.builder().fruitName(name).build();
                fruitController.creatFruit(fruitEntity);
            });
            fruitController.getfruitinfo().forEach(System.out::println);
        };
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
    @Profile("defaulta")
    ApplicationRunner initH2Pdn(AnimalController animalController) {
        return args -> {
            Stream.of("Dog", "Cat", "Bird", "Fish").forEach(name -> {
                AnimalEntity animalEntity = AnimalEntity.builder().name(name).build();
                animalController.saveAnimal(animalEntity);
            });
            animalController.getAnimals().forEach(System.out::println);
        };
    }

    @Bean
    ApplicationRunner loadNumberOrder(Numbers positions) {
        return args -> {
            positions.printPosition();
        };
    }
}
