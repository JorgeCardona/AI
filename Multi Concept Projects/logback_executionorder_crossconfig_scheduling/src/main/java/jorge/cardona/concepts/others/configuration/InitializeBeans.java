package jorge.cardona.concepts.others.configuration;

import jorge.cardona.concepts.others.order.Numbers;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitializeBeans {

    @Bean
    ApplicationRunner loadNumberOrder(Numbers positions) {
        return args -> {
            positions.printPosition();
        };
    }
}
