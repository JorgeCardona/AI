package com.dydu.hoover.interfaces;

import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;

public interface Solution {
    
    void getSolution(MatrixImplementation mI, int y_position, int x_position);
    void clean(MatrixImplementation matrix, CoordenateImplementation cI);
    CoordenateImplementation nextPosition(MatrixImplementation matrix, CoordenateImplementation cI);
    CoordenateImplementation setInitialPosition(MatrixImplementation matrix, int y_position, int x_position);

}
