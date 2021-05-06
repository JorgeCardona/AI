package com.dydu.hoover.services;

import org.junit.Assert;
import org.junit.Test;

import com.dydu.hoover.services.MatrixFileReader;

/**
 * Test MatrixFileReader methods.
 * 
 * @author mat
 */
public final class MatrixImplementationFileReaderTest {

    @Test
    public void readFile() {

        MatrixFileReader matrixFileReader = new MatrixFileReader();

        String[][] maze = matrixFileReader.readFile("/maze1.txt");

        Assert.assertNotNull(maze);

        Assert.assertEquals(7, maze[0].length);
        Assert.assertEquals("M", maze[0][0]);
        Assert.assertEquals(" ", maze[2][6]);
    }

    @Test
    public void readFileWithError() {
        Assert.assertNull(new MatrixFileReader().readFile("null"));
    }

}
