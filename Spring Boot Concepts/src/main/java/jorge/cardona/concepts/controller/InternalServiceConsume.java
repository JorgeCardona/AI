package jorge.cardona.concepts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
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
}
