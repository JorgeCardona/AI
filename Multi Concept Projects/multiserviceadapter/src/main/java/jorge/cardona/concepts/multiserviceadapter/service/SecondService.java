package jorge.cardona.concepts.multiserviceadapter.service;

import jorge.cardona.concepts.multiserviceadapter.interfaces.AdapterService;
import org.springframework.stereotype.Service;

@Service("Second")
public class SecondService implements AdapterService {

    int serviceCall = 1;

    @Override
    public String process() {
        return "SecondService " + serviceCall++ ;
    }
}
