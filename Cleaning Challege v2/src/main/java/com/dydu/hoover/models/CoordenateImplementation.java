package com.dydu.hoover.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dydu.hoover.interfaces.Coordenate;
import com.dydu.hoover.services.GenerateMatrixImplementation;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class CoordenateImplementation implements Coordenate {

    private static final Logger LOG = LoggerFactory.getLogger(CoordenateImplementation.class);

    private int y_position;
    private int x_position;

    private boolean wall;
    private boolean cleaned;

    private List<Integer> step = new ArrayList<>();

    public CoordenateImplementation(int y_position, int x_position) {
        this.y_position = y_position;
        this.x_position = x_position;
        this.step = step;
    }

    @Override
    public void addStep(int stair) { step.add(stair);}

    @Override
    public String toString() {
        return "[" + y_position + ", " + x_position + "]";
    }
}