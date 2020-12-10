package com.co.cleaning.useCase;

import com.co.cleaning.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class MatrixDijkstraImplementationTest {

    private String fileName = "/maze1.txt";
    private MatrixLoadData matrixLoadData = new MatrixLoadData();
    private MatrixDijkstraImplementation matrixDijkstraImplementation = new MatrixDijkstraImplementation();
    private Coordinate start = new Coordinate(1, 1);
    private Coordinate end = new Coordinate(2, 3);
    String[][] matrix = matrixLoadData.readFile(fileName);

    private List<Coordinate> coordinateList = Arrays.asList(start, end);


    @DisplayName("obtainRoute")
    @Test
    void obtainRoute() {

        List<List<Integer>> obtainRoute = matrixDijkstraImplementation.obtainRoute(matrix, start, end);

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,1),Arrays.asList(2,1), Arrays.asList(2,2), Arrays.asList(2,3)), obtainRoute);
    }

    @DisplayName("obtainRouteNull")
    @Test
    void obtainRouteNull() {

        Coordinate start = new Coordinate(20, 1);
        List<List<Integer>> obtainRoute = matrixDijkstraImplementation.obtainRoute(matrix, start, end);

        Assertions.assertNull(obtainRoute);
    }

    @DisplayName("validateMatrixLimits ok")
    @Test
    void validateMatrixLimitsOk() {

        boolean ok = matrixDijkstraImplementation.validateMatrixLimits(matrix, start, end);
        Assertions.assertTrue(ok);
    }

    @DisplayName("initializationDistances")
    @Test
    void initializationDistances() {

        Integer[][] distances = matrixDijkstraImplementation.initializationDistances(matrix);

        Assertions.assertEquals(13, distances.length);
    }

    @DisplayName("getCoordinatesPath")
    @Test
    void getCoordinatesPath() {

        Integer[][] distances = matrixDijkstraImplementation.initializationDistances(matrix);
        List<Coordinate> getCoordinatesPath = matrixDijkstraImplementation.getCoordinatesPath(matrix, distances, start, end);

        Assertions.assertEquals(4, getCoordinatesPath.size());
    }

    @DisplayName("obtainCoordinatesDeque")
    @Test
    void obtainCoordinatesDeque(){

        Integer[][] distances = matrixDijkstraImplementation.initializationDistances(matrix);

        Coordinate end = new Coordinate(200, 3);

        Deque<Coordinate> obtainCoordinatesDeque = matrixDijkstraImplementation.obtainCoordinatesDeque(distances, end);

        Assertions.assertTrue(obtainCoordinatesDeque.isEmpty());
    }

    @DisplayName("obtainCoordinatesDequeFail")
    @Test
    void obtainCoordinatesDequeFail() {

        Integer[][] distances = matrixDijkstraImplementation.initializationDistances(matrix);

        Deque<Coordinate> obtainCoordinatesDeque = matrixDijkstraImplementation.obtainCoordinatesDeque(distances, end);

        Assertions.assertNotNull(obtainCoordinatesDeque);
    }

    @DisplayName("getRecomendedPath")
    @Test
    void getRecomendedPath(){

        Integer[][] distances = matrixDijkstraImplementation.initializationDistances(matrix);

        Deque<Coordinate> obtainCoordinatesDeque = matrixDijkstraImplementation.obtainCoordinatesDeque(distances, end);

        List<List<Integer>> getRecomendedPath = matrixDijkstraImplementation.getRecomendedPath(obtainCoordinatesDeque);

        Assertions.assertNotNull(getRecomendedPath);
    }

    @DisplayName("addNeighbors")
    @Test
    void addNeighbors() {


        matrixDijkstraImplementation.addNeighbors(start, coordinateList, 0, 0);
    }

    @Test
    void getNeighbor() {

        Integer[][] distances = matrixDijkstraImplementation.initializationDistances(matrix);
        Coordinate coordinate = matrixDijkstraImplementation.getNeighbor(start, 1, distances);

        Assertions.assertNull(coordinate);
    }


    @DisplayName("isValid")
    @Test
    void isValid() {

        boolean value = matrixDijkstraImplementation.isValid(0, 0, 1, 1);
        Assertions.assertTrue(value);

    }

    @DisplayName("isNotValid")
    @Test
    void isNotValid() {

        boolean value = matrixDijkstraImplementation.isValid(1, 0, 1, 1);
        Assertions.assertFalse(value);

    }
}