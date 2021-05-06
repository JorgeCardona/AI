package com.dydu.hoover.services;

import com.dydu.hoover.interfaces.GenerateMatrix;
import com.dydu.hoover.interfaces.CovertTextToCoordenate;
import com.dydu.hoover.interfaces.ValidateMatrix;
import com.dydu.hoover.exceptions.MatrixException;
import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateMatrixImplementation implements GenerateMatrix {
    private static final Logger LOG = LoggerFactory.getLogger(GenerateMatrixImplementation.class);
    private ValidateMatrix validateMatrix = new ValidateMatrixImplementation();

    @Override
    public MatrixImplementation createMatrix(String[][] matrix){
        return new MatrixImplementation(createMatrixWithCoordenates(matrix));
    }

    @Override
    public CoordenateImplementation[][] createMatrixWithCoordenates(String[][] matrix) {

        try {
            validateMatrix.validateAllMatrixRestriction(matrix);
        } catch (MatrixException exception) {
            LOG.error(exception.getMessage(), exception);
            return createDefaultMatrix();
        }

        return getFinalMatrix(matrix);
    }

    @Override
    public CoordenateImplementation[][] getFinalMatrix(String[][] matrix) {

        int limitRows = matrix.length;
        int limitColumns = matrix[0].length;

        CoordenateImplementation[][] matrixCoordenates = new CoordenateImplementation[limitRows][limitColumns];
        CovertTextToCoordenate transformStringToPosition = new CovertTextToCoordenateImplementation();

        for(int y_position = 0; y_position < limitRows; y_position ++){
            for (int x_position = 0; x_position < limitColumns; x_position ++) {
                matrixCoordenates[y_position][x_position] = transformStringToPosition.textToCoordenate(matrix[y_position][x_position], y_position, x_position);
            }
        }

        return matrixCoordenates;
    }

    @Override
    public CoordenateImplementation[][] createDefaultMatrix() {
        CoordenateImplementation[][] coordenateImplementations = new CoordenateImplementation[0][0];
        return coordenateImplementations;
    }
}
