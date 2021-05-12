package jorge.cardona.concepts.controller;

import jorge.cardona.concepts.entity.Nature;
import jorge.cardona.concepts.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST})
public class EntryPoints {

    @Autowired
    NatureService natureService;

    @GetMapping(path = "/getall")
    public ResponseEntity nature(){

        return new ResponseEntity(natureService.getNatureList(), HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity save(@RequestBody Nature nature){

        return new ResponseEntity(natureService.saveNature(nature), HttpStatus.OK);
    }

    @PostMapping(path = "/savelist")
    public ResponseEntity list(@RequestBody List<Nature> nature){

        return new ResponseEntity(natureService.saveListNature(nature), HttpStatus.OK);
    }

    @GetMapping(path = "/internal")
    public ResponseEntity test(){

        return new ResponseEntity("INTERNAL SERVICE", HttpStatus.OK);
    }
}
