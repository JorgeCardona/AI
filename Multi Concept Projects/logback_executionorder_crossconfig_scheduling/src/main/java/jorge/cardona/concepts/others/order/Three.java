package jorge.cardona.concepts.others.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Three  implements Number {

    @Override
    public String getPosition() {

        return "Full Route to the file " + Three.class.getName();
    }
}
