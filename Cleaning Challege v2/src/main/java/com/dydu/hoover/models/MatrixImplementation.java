package com.dydu.hoover.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.dydu.hoover.interfaces.Matrix;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
public class MatrixImplementation implements Matrix {

    private static final Logger LOG = LoggerFactory.getLogger(MatrixImplementation.class);

    private CoordenateImplementation[][] coordenate;
    private List<CoordenateImplementation> path;

    public MatrixImplementation(CoordenateImplementation[][] cI) {
        this.coordenate = cI;
        this.path = new ArrayList<>();
    }

    @Override
    public CoordenateImplementation[][] obtainPosition() {

        if (Objects.nonNull(coordenate)){

            return coordenate;
        } else {

            return new CoordenateImplementation[0][0];
        }
    }

    @Override
    public boolean isEmpty(MatrixImplementation mI) {
        if (Objects.isNull(mI) || mI.obtainPosition().length == 0 || mI.obtainPosition()[0].length == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addPath(CoordenateImplementation cI) {

        if (Objects.nonNull(cI)) {
            cI.setCleaned(true);
            cI.addStep(path.size() + 1);
            path.add(cI);
        }
    }

    @Override
    public String toString() {

        return "Solution Path {" + path + '}';
    }
}