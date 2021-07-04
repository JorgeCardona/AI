package jorge.cardona.concepts.mappers;

import jorge.cardona.concepts.dto.AnimalDTO;
import jorge.cardona.concepts.entity.AnimalEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimalMapper {


    public AnimalDTO animalEntityToDto(AnimalEntity animalEntity){

        AnimalDTO animalDTO = AnimalDTO
                .builder()
                .id(animalEntity.getId())
                .name(animalEntity.getName())
                .type(animalEntity.getType())
                .build();

        return animalDTO;
    }

    public AnimalEntity animalDtoToEntity(AnimalDTO animalDTO){

        AnimalEntity animalEntity = AnimalEntity
                .builder()
                .id(animalDTO.getId())
                .name(animalDTO.getName())
                .type(animalDTO.getType())
                .build();

        return animalEntity;
    }

    public List<AnimalDTO> listAnimalEntityToDto (List<AnimalEntity> animalEntities){

        List<AnimalDTO> animalDTOList = new ArrayList<>();


        for (AnimalEntity animalEntity: animalEntities) {

            AnimalDTO animalDTO = AnimalDTO
                    .builder()
                    .id(animalEntity.getId())
                    .name(animalEntity.getName())
                    .type(animalEntity.getType())
                    .build();

            animalDTOList.add(animalDTO);
        }

        return animalDTOList;
    }

    public List<AnimalEntity> listAnimalDtoToEntity(List<AnimalDTO> animalDTOList){

        List<AnimalEntity> animalEntityList = new ArrayList<>();

        for (AnimalDTO animalDTO: animalDTOList) {

            AnimalEntity animalEntity = AnimalEntity
                    .builder()
                    .id(animalDTO.getId())
                    .name(animalDTO.getName())
                    .type(animalDTO.getType())
                    .build();

            animalEntityList.add(animalEntity);
        }

        return animalEntityList;
    }

}
