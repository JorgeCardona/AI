package jorge.cardona.concepts.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    Functions f = new Functions();

    @Test
    public void testAddOk(){

        int total = Functions.add(1,2);

        assertEquals(3, total);
    }

    @Test
    public void testdivideOk(){

        int total = Functions.divide(10,2);

        assertEquals(5, total);
    }

    @Test
    public void testSubstractOk(){

        int total = Functions.subtract(1,2);

        assertEquals(-1, total);
    }

    @Test
    public void testMultiplyOk(){

        int total = Functions.multiply(1,2);

        assertEquals(2, total);
    }
}