package jorge.cardona.concepts.service;

import jorge.cardona.concepts.entity.Nature;
import jorge.cardona.concepts.repository.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NatureService {

    @Autowired
    NatureRepository natureRepository;

    public List<Nature> getNatureList() {
        return natureRepository.findAll();
    }

    public Optional<Nature> getNatureById(UUID id) {
        return natureRepository.findById(id);
    }

    public Nature saveNature(Nature nature) {

        Nature n = natureRepository.save(nature);
        return n;
    }

    public List<Nature> saveListNature(List<Nature> nature) {

        List<Nature> n = natureRepository.saveAll(nature);
        return n;
    }
}
