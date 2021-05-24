package jorge.cardona.concepts.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/consume")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST})

/*
@OpenAPIDefinition(servers = { @Server(url = "https://myserver1.com"),
        @Server(url = "https://myserver2.com") },
        info = @Info(title = "the title", version = "v1", description = "My API",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")))

 */
public class InternalServiceConsume {

    @Bean(name="remoteRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    private RestTemplate rest;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/consume")
    public ResponseEntity consume(){

        String result = rest.getForObject("http://localhost:8080/api/internal", String.class);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/crossorigin")
    public ResponseEntity crossOrigin(){

        HashMap<Object, Object> map = new HashMap<>();
        map.put("Cross Origin", "Allowed");
        map.put("State", "Authorized");
        map.put("Date", LocalDateTime.now());

        return ResponseEntity.ok(map);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/url")
    public ResponseEntity url(HttpServletRequest httpServletRequest){


        HashMap<Object, Object> map = new HashMap<>();
        map.put("RequestURL ", httpServletRequest.getRequestURL());
        map.put("ContextPath ", httpServletRequest.getContextPath());
        map.put("PathInfo ", httpServletRequest.getPathInfo());
        map.put("Method ", httpServletRequest.getMethod());
        map.put("Cookies ", httpServletRequest.getCookies());
        map.put("HeaderNames ", httpServletRequest.getHeaderNames());
        map.put("UserPrincipal ", httpServletRequest.getUserPrincipal());
        map.put("Session ", httpServletRequest.getSession());
        map.put("AuthType ", httpServletRequest.getAuthType());
        map.put("Date ", LocalDateTime.now());

        return ResponseEntity.ok(map);
    }
}
