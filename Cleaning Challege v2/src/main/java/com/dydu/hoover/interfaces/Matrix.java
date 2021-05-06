package com.dydu.hoover.interfaces;

import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;

public interface Matrix {

    CoordenateImplementation[][] obtainPosition();
    boolean isEmpty(MatrixImplementation mI);
    void addPath(CoordenateImplementation cI);
}