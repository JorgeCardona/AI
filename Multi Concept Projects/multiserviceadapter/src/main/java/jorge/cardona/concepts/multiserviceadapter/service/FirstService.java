package jorge.cardona.concepts.multiserviceadapter.service;
import jorge.cardona.concepts.multiserviceadapter.interfaces.AdapterService;
import org.springframework.stereotype.Service;

@Service("First")
public class FirstService implements AdapterService {

    int serviceCall = 1;

    @Override
    public String process() {
        return "FirstService " + serviceCall++ ;
    }
}
