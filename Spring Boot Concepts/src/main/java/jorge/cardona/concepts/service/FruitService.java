package jorge.cardona.concepts.service;

import jorge.cardona.concepts.entity.Fruit;
import jorge.cardona.concepts.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

	@Autowired
	private FruitRepository fruitRepository;

	public List<Fruit> getAllfruitInfo() {

		return fruitRepository.findAll();
	}

	public Fruit getFruitByfruitId(String fruitId) {
		return fruitRepository.findById(fruitId).orElse(null);
	}

	public Fruit updatefruitInfo(String fruitId, Fruit fruits) {
		Fruit existingFruitId = fruitRepository.findById(fruitId).orElse(null);
		existingFruitId.setFruitId(fruits.getFruitId());
		existingFruitId.setFruitName(fruits.getFruitName());
		return fruitRepository.save(existingFruitId);
	}

	public Fruit createNewfruit(Fruit fruits) {
		Fruit objFruit = new Fruit();
		objFruit.setFruitId(fruits.getFruitId());
		objFruit.setFruitName(fruits.getFruitName());
		return fruitRepository.save(objFruit);
	}

	public List<Fruit> deleteFruitById(String fruitId){
		fruitRepository.deleteById(fruitId);

		return fruitRepository.findAll();
	}

	public List<Fruit> saveFruitList(List<Fruit> fruits){

		fruitRepository.saveAll(fruits);

		return fruitRepository.findAll();
	}
}
