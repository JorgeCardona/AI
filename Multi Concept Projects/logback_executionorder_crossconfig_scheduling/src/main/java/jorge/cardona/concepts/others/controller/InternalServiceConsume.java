package jorge.cardona.concepts.controller;

import jorge.cardona.concepts.others.util.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/consume")
//@CrossOrigin(origins = "http://localhost:9999", methods= {RequestMethod.GET, RequestMethod.POST})
public class InternalServiceConsume {

    private String serviceUrlInternal = "http://localhost:8080/api/actuator/env";
    private String serviceUrlExternal = "http://localhost:8080/api/actuator/health";

    @Autowired
    RequestResponse data;

    @Autowired
    private RestTemplate restInternal;

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    private RestTemplate restExternal;


    @GetMapping(path = "/internalrequest")
    public ResponseEntity internalrequest(){

        return new ResponseEntity(restInternal.getForObject(serviceUrlInternal, String.class), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/externalrequest")
    public ResponseEntity externalrequest(){

        return new ResponseEntity(restExternal.getForObject(serviceUrlExternal, String.class), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/crossorigin")
    public ResponseEntity crossOrigin(HttpServletRequest httpServletRequest){

        HashMap<Object, Object> map = new HashMap<>();
        map.put("Cross Origin State", "Allowed");
        map.put("Request State", "Authorized");
        map.put("Origin URL Client from Request", httpServletRequest.getHeader(HttpHeaders.ORIGIN));
        map.put("FullPath Service to Request", httpServletRequest.getRequestURI());
        map.put("Complete URl Server to RequestURL", httpServletRequest.getRequestURL());
        map.put("ServicePath", httpServletRequest.getServletPath());
        map.put("Headers", httpServletRequest.getLocalName());
        map.put("Date", LocalDateTime.now());

        return ResponseEntity.ok(map);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/url")
    public ResponseEntity url(HttpServletRequest httpServletRequest){

        HashMap<Object, Object> map = new HashMap<>();
        map.put("ContextPath ", httpServletRequest.getContextPath());
        map.put("Method ", httpServletRequest.getMethod());
        map.put("RequestURL ", httpServletRequest.getRequestURL());
        map.put("ServletPath ", httpServletRequest.getServletPath());
        map.put("Date ", LocalDateTime.now());
        map.put("LocalPort ", httpServletRequest.getLocalPort());

        return ResponseEntity.ok(data.response(httpServletRequest.getServletPath(), HttpStatus.OK, map));
    }
}
