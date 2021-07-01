package jorge.cardona.concepts.others.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Numbers {

    @Autowired
    List<Number> position;

    public void printPosition() {

        for(Number position : position) {
            System.out.println(position.getPosition());
        }
    }
}
