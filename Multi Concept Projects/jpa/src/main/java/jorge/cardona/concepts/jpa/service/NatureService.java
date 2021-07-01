package jorge.cardona.concepts.jpa.service;

import jorge.cardona.concepts.jpa.entity.NatureEntity;
import jorge.cardona.concepts.jpa.extendsoverride.NatureExample;
import jorge.cardona.concepts.jpa.repository.NatureInterfaceRepository;
import jorge.cardona.concepts.jpa.repository.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NatureService extends NatureExample implements NatureInterfaceRepository {

    @Autowired
    NatureRepository natureRepository;

    @Override
    public List<NatureEntity> getNatureList() {
        return natureRepository.findAll();
    }

    @Override
    public Optional<NatureEntity> getNatureById(UUID id) {
        return natureRepository.findById(id);
    }

    @Override
    public NatureEntity saveNature(NatureEntity natureEntity) {

        return natureRepository.save(natureEntity);
    }

    @Override
    public List<NatureEntity> saveListNature(List<NatureEntity> natureEntity) {

        return natureRepository.saveAll(natureEntity);
    }


    @Override
    public Integer dataNatureExample(){

        NatureService.this.setData(2);
        return NatureService.this.getData();
    }
}