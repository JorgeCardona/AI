package com.dydu.hoover;

import com.dydu.hoover.models.MatrixImplementation;
import com.dydu.hoover.services.MatrixCleanIt;
import com.dydu.hoover.interfaces.Solution;
import com.dydu.hoover.interfaces.GenerateMatrix;
import com.dydu.hoover.services.GenerateMatrixImplementation;
import com.dydu.hoover.services.MatrixFileReader;

public class HooverMain {

    public static void main(String[] args) {

        String path = "/hoover1.txt";

        MatrixFileReader matrixFileReader = new MatrixFileReader();
        String[][] matrix = matrixFileReader.readFile(path);

        GenerateMatrix createMatrix = new GenerateMatrixImplementation();

        Solution findSolution = new MatrixCleanIt();
        MatrixImplementation solution = createMatrix.createMatrix(matrix);
        findSolution.getSolution(solution, 1, 1);

        System.out.println(solution);
    }
}
