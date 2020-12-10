package com.co.cleaning.interfaces;

import com.co.cleaning.model.Coordinate;

import java.util.Deque;
import java.util.List;

public interface DijkstraImplementation {

    List<List<Integer>> obtainRoute(String[][] matrix, Coordinate start, Coordinate end);

    boolean validateMatrixLimits(String[][] matrix, Coordinate start, Coordinate end);

    Integer[][] initializationDistances(String[][] matrix);

    List<Coordinate> getCoordinatesPath(String[][] matrix, Integer[][] distances,
                                         Coordinate start, Coordinate end);

    Deque<Coordinate> obtainCoordinatesDeque(Integer[][] distances, Coordinate end);

    List<List<Integer>> getRecomendedPath(Deque<Coordinate> dequePath);

    void addNeighbors(Coordinate coord, List<Coordinate> list, Integer maxY, Integer maxX);

    Coordinate getNeighbor(Coordinate coord, Integer distance, Integer[][] distances);

    boolean isValid(Integer y, Integer x, Integer maxY, Integer maxX);
}
