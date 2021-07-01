package jorge.cardona.concepts.multiserviceadapter.controller;

import jorge.cardona.concepts.multiserviceadapter.interfaces.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service")
public class ServiceController {

    @Autowired
    private ServiceRegistry serviceRegistry;

    @GetMapping("{service}")
    public String  processGet(@PathVariable String service) {
        return serviceRegistry.getService(service).process();
    }
}
