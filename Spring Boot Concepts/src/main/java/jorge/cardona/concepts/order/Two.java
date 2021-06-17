package jorge.cardona.concepts.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class Two implements Number {

    @Override
    public String getPosition() {

        return "Just Package Name " + Two.class.getPackageName();
    }
}
