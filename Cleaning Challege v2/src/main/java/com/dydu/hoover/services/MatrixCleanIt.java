package com.dydu.hoover.services;

import com.dydu.hoover.interfaces.Solution;
import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.dydu.hoover.constants.GeneralConstants.EMPTY_MATRIX;

public class MatrixCleanIt implements Solution {

    private static final Logger LOG = LoggerFactory.getLogger(MatrixCleanIt.class);

    @Override
    public void getSolution(MatrixImplementation matrix, int y_position, int x_position) {
        if(matrix.isEmpty(matrix)){
            LOG.error(EMPTY_MATRIX);
            return;
        }

        CoordenateImplementation initialPosition = setInitialPosition(matrix, y_position, x_position);
        clean(matrix, initialPosition);
    }

    @Override
    public void clean(MatrixImplementation matrix, CoordenateImplementation cI) {

        matrix.addPath(cI);
        CoordenateImplementation next = nextPosition(matrix, cI);

        if(Objects.isNull(next)){
            return;
        }

        if(!next.isCleaned()){
            clean(matrix, next);
            clean(matrix, cI);
        }
    }

    @Override
    public CoordenateImplementation nextPosition(MatrixImplementation matrix, CoordenateImplementation cI) {

        List<CoordenateImplementation> coordenates = new ArrayList<>();

        int y_position = cI.getY_position();
        int x_position = cI.getX_position();

        coordenates.add(matrix.obtainPosition()[y_position][x_position + 1]);
        coordenates.add(matrix.obtainPosition()[y_position][x_position - 1]);
        coordenates.add(matrix.obtainPosition()[y_position + 1][x_position]);
        coordenates.add(matrix.obtainPosition()[y_position - 1][x_position]);

        for (CoordenateImplementation ci : coordenates) {
            if (!ci.isWall() && !ci.isCleaned()) {
                return ci;
            }
        }
        return null;
    }

    @Override
    public CoordenateImplementation setInitialPosition(MatrixImplementation matrix, int y_position, int x_position) {
        if(matrix.isEmpty(matrix)){
            return new CoordenateImplementation(0,0);
        }
        return matrix.obtainPosition()[y_position][x_position];
    }
}