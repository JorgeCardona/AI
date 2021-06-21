package jorge.cardona.concepts.adapter.service;

import jorge.cardona.concepts.adapter.interfaces.AdapterService;
import org.springframework.stereotype.Service;

@Service("First")
public class FirstService implements AdapterService {

    int serviceCall = 1;

    @Override
    public String process() {
        return "FirstService " + serviceCall++ ;
    }
}
