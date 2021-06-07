package jorge.cardona.concepts.controller;

import jorge.cardona.concepts.entity.Fruit;
import jorge.cardona.concepts.repository.FruitRepository;
import jorge.cardona.concepts.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/fruitinfo")
public class FruitController {
	
	@Autowired
	private FruitService fruitService;
	
	@Autowired
	private FruitRepository fruitRepository;
	
	// get all fruit info
	@GetMapping(value = "/allfruits", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Fruit> getfruitinfo() {
		return fruitService.getAllfruitInfo();
	}
	
	//get fruit info by id
	@GetMapping(value = "/{fruitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Fruit getFruitById(@PathVariable("fruitId") String fruitId) {
		return fruitService.getFruitByfruitId(fruitId);
	}
	
	
	// update fruit by id
	@PutMapping(value = "/update/{fruitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Fruit updateUser(@PathVariable("fruitId") String fruitId, @RequestBody Fruit fruits) {
		return fruitService.updatefruitInfo(fruitId, fruits);
	}
	
	
	// create new fruit
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public Fruit creatFruit(@RequestBody Fruit fruit) {
		return fruitService.createNewfruit(fruit);
	}
	
	
	// delete existing fruit
	@DeleteMapping(value = "/delete/{fruitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Fruit> deleteFruitById(@PathVariable("fruitId") String fruitId) {

		return fruitService.deleteFruitById(fruitId);

	}

	// delete existing fruit
	@PostMapping(value = "/saveall", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Fruit> deleteFruitById(@RequestBody List<Fruit> fruits) {

		return fruitService.saveFruitList(fruits);

	}

}
