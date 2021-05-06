package com.dydu.hoover.services;

import java.util.ArrayList;

import com.dydu.hoover.constants.GeneralConstants;
import com.dydu.hoover.constants.TestConstants;
import com.dydu.hoover.interfaces.GenerateMatrix;
import com.dydu.hoover.interfaces.Solution;
import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CleanMatrixTest {

    private GenerateMatrix object = new GenerateMatrixImplementation();
    private CoordenateImplementation coordenates;
    private CoordenateImplementation coordenatesWall;
    private MatrixImplementation matrixImplementation;
    private MatrixImplementation matrixImplementationEmpty;
    private ArrayList<String> path = new ArrayList<>();
    private Solution cleanMatrix = new MatrixCleanIt();
    private String pathSolution = "Solution Path {%s}";
    public static final String IS_EMPTY = GeneralConstants.IS_EMPTY;

    @Before
    public void setUp() throws Exception {
        object = new GenerateMatrixImplementation();
        matrixImplementation = object.createMatrix(TestConstants.matrix4x4x1);
        coordenates = new MatrixCleanIt().setInitialPosition(matrixImplementation, 1, 1);
        coordenatesWall = new MatrixCleanIt().setInitialPosition(matrixImplementation, 0, 0);
        matrixImplementationEmpty = object.createMatrix(null);
        path.add("[1, 1], [1, 2], [1, 1]");

        cleanMatrix.getSolution(matrixImplementation, 1 ,1);
    }


    @Test
    @DisplayName("matrixIsWall")
    public void matrixIsWall() {

        assertTrue(coordenatesWall.isWall());
    }

    @Test
    @DisplayName("matrixIsCleaned")
    public void matrixIsCleaned() {

        assertTrue(coordenates.isCleaned());
    }

    @Test
    @DisplayName("getPositionIntoMatrix")
    public void getPositionIntoMatrix() {
        assertTrue(coordenates.getY_position() == 1 && coordenates.getX_position() == 1);
    }

    @Test
    @DisplayName("matrixIsEmpty")
    public void matrixIsEmpty() {

        assertTrue(matrixImplementation.isEmpty(matrixImplementationEmpty));
        assertEquals(GeneralConstants.IS_EMPTY, " ");
    }

    @Test
    @DisplayName("pathSolution")
    public void pathSolution() {

        Solution findSolution = new MatrixCleanIt();
        MatrixImplementation solution = object.createMatrix(TestConstants.matrix4x4x2);
        findSolution.getSolution(solution, 1, 1);

        assertEquals(solution.toString(), String.format(pathSolution, path));
    }
}