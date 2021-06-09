package jorge.cardona.concepts.controller;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.swagger.v3.oas.annotations.Hidden;
import jorge.cardona.concepts.entity.Animal;
import jorge.cardona.concepts.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
@AllArgsConstructor
public class AnimalController {

    private final AnimalRepository animalRepository;

    @GraphQLQuery(name = "animals") // READ ALL
    public List<Animal> getAnimals() {
        return (List<Animal>) animalRepository.findAll();
    }

    @GraphQLQuery(name = "animal") // READ BY ID
    public Optional<Animal> getAnimalById(@GraphQLArgument(name = "id") Long id) {
        return animalRepository.findById(id);
    }

    @GraphQLMutation(name = "saveAnimal") // CREATE
    public Animal saveAnimal(@GraphQLArgument(name = "animal") Animal animal) {
        return animalRepository.save(animal);
    }

    @GraphQLMutation(name = "saveAnimalList") // CREATE LIST
    public Iterable<Animal> saveAnimalList(@GraphQLArgument(name = "animalList") List<Animal> animalList) {
        return animalRepository.saveAll(animalList);
    }

    @GraphQLMutation(name = "deleteAnimal") // DELETE
    public void deleteAnimal(@GraphQLArgument(name = "id") Long id) {
        animalRepository.deleteById(id);
    }

    @GraphQLQuery(name = "isFriendly") // Calculated property of animal
    public boolean tender(@GraphQLArgument(name = "friend") Animal animal) {
        return !Arrays.asList("Shark", "Snake").contains(animal.getName());
    }
}