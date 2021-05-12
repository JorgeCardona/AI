package jorge.cardona.concepts.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class Functions {

    public static int sum (int x, int y){

        return x + y;
    }
}
