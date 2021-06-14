package jorge.cardona.concepts.service;

import jorge.cardona.concepts.entity.Nature;
import jorge.cardona.concepts.repository.NatureInterfaceRepository;
import jorge.cardona.concepts.repository.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NatureService implements NatureInterfaceRepository {

    @Autowired
    NatureRepository natureRepository;

    public List<Nature> getNatureList() {
        return natureRepository.findAll();
    }

    public Optional<Nature> getNatureById(UUID id) {
        return natureRepository.findById(id);
    }


    public Nature saveNature(Nature nature) {

        return natureRepository.save(nature);
    }

    public List<Nature> saveListNature(List<Nature> nature) {

        return natureRepository.saveAll(nature);
    }
}
