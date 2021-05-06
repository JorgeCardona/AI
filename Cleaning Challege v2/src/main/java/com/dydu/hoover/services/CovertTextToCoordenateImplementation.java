package com.dydu.hoover.services;

import com.dydu.hoover.interfaces.CovertTextToCoordenate;
import com.dydu.hoover.models.CoordenateImplementation;
import com.dydu.hoover.models.MatrixImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dydu.hoover.constants.GeneralConstants.IS_EMPTY;
import static com.dydu.hoover.constants.GeneralConstants.IS_WALL;

public class CovertTextToCoordenateImplementation implements CovertTextToCoordenate {

    private static final Logger LOG = LoggerFactory.getLogger(CovertTextToCoordenateImplementation.class);

    private CoordenateImplementation coordenateImplementation;

    @Override
    public CoordenateImplementation textToCoordenate(String actual_value, int y_position, int x_position) {
        coordenateImplementation = new CoordenateImplementation(y_position, x_position);

        if (IS_WALL.equalsIgnoreCase(actual_value)) {
            setCoordinateValue(true);
        } else if(IS_EMPTY.equalsIgnoreCase(actual_value)){
            setCoordinateValue(false);
        }
        return coordenateImplementation;
    }

    public void setCoordinateValue(boolean value) {

        coordenateImplementation.setCleaned(value);
        coordenateImplementation.setWall(value);
    }
}
