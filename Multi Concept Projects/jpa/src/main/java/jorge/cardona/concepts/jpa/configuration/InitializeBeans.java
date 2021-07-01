package jorge.cardona.concepts.jpa.configuration;

import jorge.cardona.concepts.jpa.controller.NatureController;
import jorge.cardona.concepts.jpa.entity.NatureEntity;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class InitializeBeans {

//    @Bean
//    ApplicationRunner initEmbeddedMongo(NatureController natureController) {
//        return args -> {
//            Stream.of("Banana", "Guama", "Chontaduro", "Coconut").forEach(name -> {
//                NatureEntity natureEntity = NatureEntity.builder().fruitName(name).build();
//                natureController.save(natureEntity);
//            });
//            natureController.natureJson()
//            fruitController.getfruitinfo().forEach(System.out::println);
//        };
//    }
}
