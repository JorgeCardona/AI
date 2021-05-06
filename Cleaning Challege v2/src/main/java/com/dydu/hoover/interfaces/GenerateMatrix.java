package com.dydu.hoover.interfaces;

import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;

public interface GenerateMatrix {

    MatrixImplementation createMatrix(String[][] matrix);
    CoordenateImplementation[][] createMatrixWithCoordenates(String[][] matrix);
    CoordenateImplementation[][] getFinalMatrix(String[][] matrix);
    CoordenateImplementation[][] createDefaultMatrix();
}
