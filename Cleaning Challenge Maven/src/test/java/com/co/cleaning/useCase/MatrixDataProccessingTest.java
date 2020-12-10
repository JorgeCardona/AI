package com.co.cleaning.useCase;

import com.co.cleaning.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixDataProccessingTest {

    private MatrixDataProccessing matrixDataProccessing = new MatrixDataProccessing();
    private List<List<Integer>> elements = Arrays.asList(Arrays.asList(1,2),Arrays.asList(1,2),Arrays.asList(1,3));
    private List<List<Integer>> others = Arrays.asList(Arrays.asList(2,2),Arrays.asList(3,2),Arrays.asList(4,2));
    private List<List<Integer>> join = Arrays.asList(Arrays.asList(1,1), Arrays.asList(2,1), Arrays.asList(2,2));
    private List<List<Integer>> joinOne = Arrays.asList(Arrays.asList(1,1));
    private Coordinate startPosition = new Coordinate(1,3);
    private List<List<List<Integer>>> allPath = Arrays.asList(elements, others);

    private  MatrixDijkstraImplementation matrixDijkstraImplementation = new MatrixDijkstraImplementation();

    private String fileName = "/maze1.txt";
    private MatrixLoadData matrixLoadData = new MatrixLoadData();
    String[][] matrix = matrixLoadData.readFile(fileName);

    MatrixExtractInformation matrixExtractInformation = new MatrixExtractInformation();

    List<List<String>> dataList = matrixExtractInformation.convertArraytoList(matrix);
    List<List<Integer>> allCoordinates = matrixExtractInformation.obtainPositionsForCleaning(dataList);

    @Test
    void getAllPath() {

        List<List<List<Integer>>> getAllPath = matrixDataProccessing.getAllPath(matrix, allCoordinates);

        Assertions.assertEquals(10, getAllPath.size());
    }

    @Test
    void joinPath() {

        List<List<Integer>> joinPath =  matrixDataProccessing.joinPath(join, matrix);
        List<List<Integer>> joinPathOne =  matrixDataProccessing.joinPath(joinOne, matrix);

        Assertions.assertAll(
                () -> assertEquals(Arrays.asList(Arrays.asList(1,1)), joinPathOne),
                () -> assertEquals(Arrays.asList(Arrays.asList(1,1),Arrays.asList(2,1), Arrays.asList(2,2)), joinPath)
        );
    }

    @Test
    void getRoute() {

        List<List<Integer>> getRoute = matrixDataProccessing.getRoute(matrix, Arrays.asList(1,1), Arrays.asList(2,3));

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,1),Arrays.asList(2,1), Arrays.asList(2,2), Arrays.asList(2,3)), getRoute);

    }

    @Test
    void getPathSolution() {

        List<List<Integer>> getPathSolution =  matrixDataProccessing.getPathSolution(startPosition, allPath);

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,3),Arrays.asList(1,2), Arrays.asList(1,3)), getPathSolution);
    }

    @Test
    void existsInPath() {

        boolean indexInPath = matrixDataProccessing.existsInPath(elements, startPosition);

        Assertions.assertEquals(true, indexInPath);
    }

    @Test
    void indexInPath() {

        Integer indexInPath = matrixDataProccessing.indexInPath(elements, startPosition);

        Assertions.assertEquals(2, indexInPath);
    }

    @Test
    void completePath() {

        List<List<Integer>> completePath = matrixDataProccessing.completePath(elements, 2);

        System.out.println(completePath);

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,3),Arrays.asList(1,2),Arrays.asList(1,2),Arrays.asList(1,2), Arrays.asList(1,2), Arrays.asList(1,3), Arrays.asList(1,3)), completePath);

    }

    @Test
    void leftSideWay() {

        List<List<Integer>> leftSideWay = matrixDataProccessing.leftSideWay(elements, 2);

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,3),Arrays.asList(1,2),Arrays.asList(1,2),Arrays.asList(1,2), Arrays.asList(1,2),Arrays.asList(1,3)), leftSideWay);
    }

    @Test
    void rightSideWay() {

        List<List<Integer>> rightSideWay = matrixDataProccessing.rightSideWay(elements, 1);

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,2),Arrays.asList(1,3)), rightSideWay);
    }

    @Test
    void removeDuplicateElement() {

        List<List<Integer>> removeDuplicateElement  = matrixDataProccessing.removeDuplicateElement(elements);

        Assertions.assertEquals(Arrays.asList(Arrays.asList(1,2),Arrays.asList(1,3)), removeDuplicateElement);
    }
}