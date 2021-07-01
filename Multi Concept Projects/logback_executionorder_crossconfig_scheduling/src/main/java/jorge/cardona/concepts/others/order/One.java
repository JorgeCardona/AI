package jorge.cardona.concepts.others.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class One implements Number {

    @Override
    public String getPosition() {

        return "Just Class name " + One.class.getSimpleName();
    }
}
