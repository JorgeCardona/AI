package jorge.cardona.concepts.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    Functions f = new Functions();

    @Test
    public void testOk(){

        int total = Functions.sum(1,2);

        assertEquals(3, total);
    }
}