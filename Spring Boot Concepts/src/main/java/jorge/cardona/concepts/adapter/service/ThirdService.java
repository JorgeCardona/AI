package jorge.cardona.concepts.adapter.service;

import jorge.cardona.concepts.adapter.interfaces.AdapterService;
import org.springframework.stereotype.Service;

@Service("Third")
public class ThirdService implements AdapterService {

    int serviceCall = 1;

    @Override
    public String process() {
        return "ThirdService " + serviceCall++ ;
    }
}
