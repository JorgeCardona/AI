package jorge.cardona.concepts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST})
public class InternalServiceConsume {

    @Bean(name="remoteRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    private RestTemplate rest;

    @GetMapping(path = "/consume")
    public ResponseEntity consume(){

        String result = rest.getForObject("http://localhost:8080/api/internal", String.class);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(path = "/crossorigin")
    public ResponseEntity crossOrigin(){

        HashMap<Object, Object> map = new HashMap<>();
        map.put("Cross Origin", "Allowed");
        map.put("State", "Authorized");
        map.put("Date", LocalDateTime.now());

        return ResponseEntity.ok(map);
    }
}
