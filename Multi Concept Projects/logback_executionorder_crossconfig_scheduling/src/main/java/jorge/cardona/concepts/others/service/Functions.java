package jorge.cardona.concepts.others.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class Functions {

    public static int add (int x, int y){

        return x + y;
    }

    public static int multiply (int x, int y){

        return x * y;
    }

    public static int subtract (int x, int y){

        return x - y;
    }

    public static int divide (int x, int y){

        return x / y;
    }
}
