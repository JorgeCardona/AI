package com.dydu.hoover.interfaces;

import com.dydu.hoover.models.CoordenateImplementation;

public interface CovertTextToCoordenate {

    CoordenateImplementation textToCoordenate(String actual_value, int y_position, int x_position);
}