package com.dydu.hoover.interfaces;

import com.dydu.hoover.exceptions.MatrixException;

public interface ValidateMatrix {

    void validateAllMatrixRestriction(String[][] matrix) throws MatrixException;
    void validateNotEmpty(String[][] matrix) throws MatrixException;
    void validateLimits(String[][] matrix) throws MatrixException;
    void validateAllowedValues(String[][] matrix)  throws MatrixException;
    void validateAvailableSpaces (String[][] matrix) throws MatrixException;
}
