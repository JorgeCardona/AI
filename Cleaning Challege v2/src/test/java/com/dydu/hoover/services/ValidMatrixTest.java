package com.dydu.hoover.services;

import com.dydu.hoover.constants.TestConstants;
import com.dydu.hoover.exceptions.MatrixException;
import com.dydu.hoover.interfaces.GenerateMatrix;
import com.dydu.hoover.interfaces.ValidateMatrix;
import com.dydu.hoover.models.CoordenateImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class ValidMatrixTest {

    private GenerateMatrix object = new GenerateMatrixImplementation();
    CoordenateImplementation[][] coordenateImplementations;
    ValidateMatrix validateMatrix = new ValidateMatrixImplementation();

    @Before
    public void setUp() throws Exception {
        object = new GenerateMatrixImplementation();
        coordenateImplementations = object.createMatrixWithCoordenates(TestConstants.matrix4x4);
    }

    @Test
    @DisplayName("matrixTestFullLenght")
    public void matrixTestFullLenght() {
        assertEquals(coordenateImplementations.length, TestConstants.matrix4x4.length);
    }

    @Test
    @DisplayName("matrixRowLenght")
    public void matrixRowLenght() {

        assertEquals(coordenateImplementations[0].length, TestConstants.matrix4x4[0].length);
    }

    @Test
    @DisplayName("matrixColumnLenght")
    public void matrixColumnLenght() {

        assertEquals(coordenateImplementations[1].length, TestConstants.matrix4x4[1].length);
    }

    @Test
    @DisplayName("matrixIsCleaned")
    public void matrixIsCleaned() {
        assertFalse(coordenateImplementations[1][1].isCleaned());
    }

    @Test
    @DisplayName("matrixIsWall")
    public void matrixIsWall() {
        assertTrue(coordenateImplementations[0][0].isWall());
    }

    @Test
    @DisplayName("matrixIsEmpty")
    public void matrixIsEmpty() {

        coordenateImplementations = object.createMatrixWithCoordenates(null);
        assertEquals(coordenateImplementations.length, 0);
    }

    @Test
    @DisplayName("noValidMatrix")
    public void noValidMatrix() throws MatrixException {

        boolean validation;

        try {
            validateMatrix.validateAllMatrixRestriction(TestConstants.matrix4x4x0);
            validation = true;
        } catch (Exception ex) {
            validation = false;
        }
        assertFalse(validation);
    }
}