package jorge.cardona.concepts.mongodb.configuration;

import jorge.cardona.concepts.mongodb.controller.FruitController;
import jorge.cardona.concepts.mongodb.entity.FruitEntity;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class InitializeBeans {

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
