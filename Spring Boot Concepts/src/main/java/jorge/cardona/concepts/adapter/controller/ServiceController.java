package jorge.cardona.concepts.adapter.controller;

import jorge.cardona.concepts.adapter.interfaces.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/service")
public class ServiceController {

    @Autowired
    private ServiceRegistry serviceRegistry;

    @GetMapping("{service}")
    public String  processGet(@PathVariable String service) {
        return serviceRegistry.getService(service).process();
    }

    @GetMapping(value = "/adapter")
    public String  processGetParameterVariable(@RequestParam(name="adapter", required = true, defaultValue = "First") String adapter) {
        return serviceRegistry.getService(adapter).process();
    }
}
