package jorge.cardona.concepts.controller;

import jorge.cardona.concepts.entity.FruitEntity;
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
	public List<FruitEntity> getfruitinfo() {
		return fruitService.getAllfruitInfo();
	}
	
	//get fruit info by id
	@GetMapping(value = "/{fruitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FruitEntity getFruitById(@PathVariable("fruitId") String fruitId) {
		return fruitService.getFruitByfruitId(fruitId);
	}
	
	
	// update fruit by id
	@PutMapping(value = "/update/{fruitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FruitEntity updateUser(@PathVariable("fruitId") String fruitId, @RequestBody FruitEntity fruits) {
		return fruitService.updatefruitInfo(fruitId, fruits);
	}
	
	
	// create new fruit
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public FruitEntity creatFruit(@RequestBody FruitEntity fruitEntity) {
		return fruitService.createNewfruit(fruitEntity);
	}
	
	
	// delete existing fruit
	@DeleteMapping(value = "/delete/{fruitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FruitEntity> deleteFruitById(@PathVariable("fruitId") String fruitId) {

		return fruitService.deleteFruitById(fruitId);

	}

	// delete existing fruit
	@PostMapping(value = "/saveall", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FruitEntity> deleteFruitById(@RequestBody List<FruitEntity> fruitEntities) {

		return fruitService.saveFruitList(fruitEntities);

	}

}
