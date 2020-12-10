package com.co.cleaning.useCase;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixLoadDataTest {

    private MatrixLoadData matrixLoadData = new MatrixLoadData();

    @Test
    public void readFile() {

        String[][] maze = matrixLoadData.readFile("/maze1.txt");

        Assertions.assertAll(
                () -> assertEquals(17, maze[0].length),
                () -> assertEquals("M", maze[0][0]),
                () -> assertEquals(" ", maze[2][7])
        );
    }

    @Test
    public void readFileWithError() {
        Assert.assertNull(matrixLoadData.readFile("null"));
    }

    @Test
    public void fillMatrix() {

        String[] maze = {"M"};
        String[][] fillMatrix = matrixLoadData.fillMatrix(maze, 2);

        Assert.assertEquals(1, fillMatrix.length);
    }
}