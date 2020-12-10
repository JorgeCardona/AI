package com.co.cleaning.useCase;

import com.co.cleaning.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatrixExtractInformationTest {

    private String fileName = "/maze1.txt";
    private MatrixLoadData matrixLoadData = new MatrixLoadData();
    private MatrixExtractInformation matrixExtractInformation = new MatrixExtractInformation();
    private Coordinate start = new Coordinate(1, 1);
    private Coordinate end = new Coordinate(2, 3);
    String[][] matrix = matrixLoadData.readFile(fileName);

    @Test
    void printMatrix() {

        String printMatrix = matrixExtractInformation.printMatrix(matrix);

        Assertions.assertAll(
                () -> assertEquals(689, printMatrix.length()),
                () -> assertNotNull(printMatrix)
        );
    }

    @Test
    void convertArraytoList() {

        List<List<String>> convertArraytoList = matrixExtractInformation.convertArraytoList(matrix);

        Assertions.assertAll(
                () -> assertEquals(13, convertArraytoList.size()),
                () -> assertEquals(convertArraytoList.getClass(), java.util.ArrayList.class)
        );
    }

    @Test
    void obtainPositionsForCleaning() {

        List<List<String>> convertArraytoList = matrixExtractInformation.convertArraytoList(matrix);
        List<List<Integer>> obtainPositionsForCleaning = matrixExtractInformation.obtainPositionsForCleaning(convertArraytoList);

        Assertions.assertAll(
                () -> assertEquals(86, obtainPositionsForCleaning.size()),
                () -> assertEquals(obtainPositionsForCleaning.getClass(), java.util.ArrayList.class)
        );
    }

    @Test
    void validateMatrixLimits() {

        List<List<String>> convertArraytoList = matrixExtractInformation.convertArraytoList(matrix);
        boolean validateMatrixLimits = matrixExtractInformation.validateMatrixLimits(convertArraytoList);

        Assertions.assertTrue(validateMatrixLimits);

    }

    @Test
    void obtainMatrixValues() {

        List<List<String>> convertArraytoList = matrixExtractInformation.convertArraytoList(matrix);
        Map<String, Long> obtainMatrixValues = matrixExtractInformation.obtainMatrixValues(convertArraytoList);

        Assertions.assertAll(
                () -> assertEquals(86, obtainMatrixValues.get("dirtySpaces")),
                () -> assertEquals(221, obtainMatrixValues.get("totalSpaces")),
                () -> assertEquals(0, obtainMatrixValues.get("cleanSpaces")),
                () -> assertEquals(135, obtainMatrixValues.get("wallSpaces")),
                () -> assertEquals(4, obtainMatrixValues.size())
        );

    }
}