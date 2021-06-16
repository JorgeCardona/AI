package jorge.cardona.concepts.service;

import jorge.cardona.concepts.entity.FruitEntity;
import jorge.cardona.concepts.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

	@Autowired
	private FruitRepository fruitRepository;

	public List<FruitEntity> getAllfruitInfo() {

		return fruitRepository.findAll();
	}

	public FruitEntity getFruitByfruitId(String fruitId) {
		return fruitRepository.findById(fruitId).orElse(null);
	}

	public FruitEntity updatefruitInfo(String fruitId, FruitEntity fruits) {
		FruitEntity existingFruitEntityId = fruitRepository.findById(fruitId).orElse(null);
		existingFruitEntityId.setFruitId(fruits.getFruitId());
		existingFruitEntityId.setFruitName(fruits.getFruitName());
		return fruitRepository.save(existingFruitEntityId);
	}

	public FruitEntity createNewfruit(FruitEntity fruits) {
		FruitEntity objFruitEntity = new FruitEntity();
		objFruitEntity.setFruitId(fruits.getFruitId());
		objFruitEntity.setFruitName(fruits.getFruitName());
		return fruitRepository.save(objFruitEntity);
	}

	public List<FruitEntity> deleteFruitById(String fruitId){
		fruitRepository.deleteById(fruitId);

		return fruitRepository.findAll();
	}

	public List<FruitEntity> saveFruitList(List<FruitEntity> fruitEntities){

		fruitRepository.saveAll(fruitEntities);

		return fruitRepository.findAll();
	}
}
