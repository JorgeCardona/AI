package jorge.cardona.concepts.controller;

import jorge.cardona.concepts.entity.Nature;
import jorge.cardona.concepts.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/nature")

@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST})
public class EntryPoints {

    @Autowired
    NatureService natureService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getall")
    @ResponseBody
    public ResponseEntity natureJson(){

        return ResponseEntity.ok(natureService.getNatureList());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/save")
    public ResponseEntity save(@Valid @RequestBody Nature nature, Errors errors){

        if(errors.hasErrors()){

            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        return new ResponseEntity(natureService.saveNature(nature), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/savelist")
    public ResponseEntity list(@RequestBody List<Nature> nature){

        return new ResponseEntity(natureService.saveListNature(nature), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/internal")
    public ResponseEntity test(){

        return new ResponseEntity("INTERNAL SERVICE", HttpStatus.OK);
    }
}