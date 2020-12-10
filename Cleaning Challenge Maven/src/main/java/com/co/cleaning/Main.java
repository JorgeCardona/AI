package com.co.cleaning;

import com.co.cleaning.useCase.MatrixDataProccessing;
import com.co.cleaning.model.Coordinate;
import com.co.cleaning.useCase.MatrixDijkstraImplementation;
import com.co.cleaning.useCase.MatrixExtractInformation;
import com.co.cleaning.useCase.MatrixLoadData;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String pathFile = "/hoover1.txt";

        MatrixLoadData matrixLoadData = new MatrixLoadData();
        MatrixExtractInformation matrixExtractInformation = new MatrixExtractInformation();
        MatrixDataProccessing matrixDataProccessing = new MatrixDataProccessing();
        MatrixDijkstraImplementation matrixDijkstraImplementation = new MatrixDijkstraImplementation();

        // read file from matrix
        String[][] dataFromFile = matrixLoadData.readFile(pathFile);

        // convert the string to list
        List<List<String>> dataList = matrixExtractInformation.convertArraytoList(dataFromFile);

        // check it is a valid matrix
        boolean validateMatrixLimits = matrixExtractInformation.validateMatrixLimits(dataList);

        if(validateMatrixLimits){

            // obtains all coordinates for cleaning of matrix
            List<List<Integer>> allCoordinates = matrixExtractInformation.obtainPositionsForCleaning(dataList);

            // found every posible path for cleaning
            List<List<List<Integer>>> getAllPath = matrixDataProccessing.getAllPath(dataFromFile, allCoordinates);

            // its the coordenate that wants to start
            Coordinate buscar = new Coordinate(0, 0);

            List<List<Integer>> solution = matrixDataProccessing.getPathSolution(buscar, getAllPath);

            if(!solution.isEmpty()){

                System.out.println("THE PATH FOR CLEANNING IT'S " + solution);
            } else{

                System.out.println("DO NOT EXISTS PATH FOR CLEANING");
            }

        } else{

            System.out.println("NO VALID MATRIX FOR PROCESSING");
        }
    }
}
