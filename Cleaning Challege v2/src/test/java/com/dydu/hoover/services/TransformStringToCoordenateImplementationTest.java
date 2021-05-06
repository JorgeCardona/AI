package com.dydu.hoover.services;

import java.util.Arrays;
import java.util.List;

import com.dydu.hoover.interfaces.CovertTextToCoordenate;
import com.dydu.hoover.models.CoordenateImplementation;
import org.junit.Before;
import org.junit.Test;

import static com.dydu.hoover.constants.GeneralConstants.IS_EMPTY;
import static com.dydu.hoover.constants.GeneralConstants.IS_WALL;
import static org.junit.Assert.*;

public class TransformStringToCoordenateImplementationTest {

    CovertTextToCoordenate convert;
    CoordenateImplementation coordenates;
    String wall = IS_WALL;
    List<Integer> wall_positions;
    String empty = IS_EMPTY;
    List<Integer> empty_positions;

    @Before
    public void setUp() throws Exception {

        convert = new CovertTextToCoordenateImplementation();
        wall_positions = Arrays.asList(1,2);
        empty_positions = Arrays.asList(3,7);
    }

    @Test
    public void should_return_a_position_object_with_a_wall() {

        coordenates = convert.textToCoordenate(wall, wall_positions.get(0), wall_positions.get(1));

        assertEquals(coordenates.getY_position(), 1);
        assertEquals(coordenates.getX_position(), 2);
        assertTrue(coordenates.isWall());
    }

    @Test
    public void should_return_a_position_object_with_a_cell_IS_EMPTY() {

        coordenates = convert.textToCoordenate(empty, empty_positions.get(0), empty_positions.get(1));

        assertEquals(coordenates.getY_position(), 3);
        assertEquals(coordenates.getX_position(), 7);
        assertFalse(coordenates.isCleaned());
    }
}