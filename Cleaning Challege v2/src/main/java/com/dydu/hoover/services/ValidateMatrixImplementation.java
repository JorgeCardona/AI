package com.dydu.hoover.services;

import com.dydu.hoover.interfaces.ValidateMatrix;
import com.dydu.hoover.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import static com.dydu.hoover.constants.GeneralConstants.IS_EMPTY;
import static com.dydu.hoover.constants.GeneralConstants.IS_WALL;

public class ValidateMatrixImplementation implements ValidateMatrix {

    private static final Logger LOG = LoggerFactory.getLogger(ValidateMatrixImplementation.class);

    @Override
    public void validateAllMatrixRestriction(String[][] matrix) throws MatrixException {
        validateNotEmpty(matrix);
        validateLimits(matrix);
        validateAllowedValues(matrix);
        validateAvailableSpaces(matrix);
    }

    @Override
    public void validateNotEmpty(String[][] matrix) throws MatrixException {
        if (Objects.isNull(matrix) || matrix.length == 0 || matrix[0].length == 0){
            throw new MatrixException("");
        }
    }

    @Override
    public void validateLimits(String[][] matrix) throws MatrixException {

        int limitRows = matrix.length;
        int limitColumns = matrix[0].length;

        for (int x = 0; x < limitRows; x++) {
            if(!matrix[x][0].equalsIgnoreCase(IS_WALL) || !matrix[x][limitColumns - 1].equalsIgnoreCase(IS_WALL)
            ){
                throw new MatrixException("");
            }
        }

        for (int x = 1; x < limitColumns; x++) {
            if(!matrix[0][x].equalsIgnoreCase(IS_WALL) || !matrix[limitRows - 1][x].equalsIgnoreCase(IS_WALL)
            ){
                throw new MatrixException("");
            }
        }
    }

    @Override
    public void validateAllowedValues(String[][] matrix)  throws MatrixException{

        int limitRows = matrix.length;
        int limitColumns = matrix[0].length;

        for (int row = 1; row < limitRows - 1; row++) {
            for (int col = 1; col < limitColumns - 1; col++) {
                if (!matrix[row][col].equalsIgnoreCase(IS_WALL) && !matrix[row][col].equalsIgnoreCase(IS_EMPTY)) {
                    throw new MatrixException("");
                }
            }
        }
    }

    @Override
    public void validateAvailableSpaces (String[][] matrix) throws MatrixException {

        int limitRows = matrix.length;
        int limitColumns = matrix[0].length;
        int freePositions = 0;

        for (int row = 1; row < limitRows - 1; row++) {
            for (int col = 1; col < limitColumns - 1; col++) {
                if( matrix[row][col].equalsIgnoreCase(IS_EMPTY) ){
                    freePositions ++;
                }
            }
        }

        if(freePositions <= 0){
            throw new MatrixException("");
        }
    }
}
