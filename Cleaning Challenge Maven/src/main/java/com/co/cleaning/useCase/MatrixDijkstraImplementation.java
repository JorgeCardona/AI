package com.co.cleaning.useCase;

import com.co.cleaning.interfaces.DijkstraImplementation;
import com.co.cleaning.model.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MatrixDijkstraImplementation implements DijkstraImplementation {

    private static final Logger LOG = LoggerFactory
            .getLogger(MatrixDijkstraImplementation.class);

    // return the route between 2 points
    @Override
    public List<List<Integer>> obtainRoute(String[][] matrix, Coordinate start, Coordinate end) {

        List<List<Integer>> route = null;

        // validate limits of matrix with coordenates request
        if(validateMatrixLimits(matrix,  start, end)){

            Integer[][] distances = initializationDistances(matrix);

            // find the shortest path between 2 points
            getCoordinatesPath(matrix, distances, start, end);

            // find the size path between 2 points
            Deque<Coordinate> path = obtainCoordinatesDeque(distances, new Coordinate(end.getY(), end.getX()));

            // extract the path for the positions
            route = getRecomendedPath(path);

            // return the route for coordinates
            return route;

        }
        // its out of range of matrix
        return  route;
    }

    // validate matrix limits
    @Override
    public boolean validateMatrixLimits(String[][] matrix, Coordinate start, Coordinate end) {
        return matrix.length > start.getY() && matrix.length > end.getY()
                && matrix[0].length > start.getX() && matrix[0].length > end.getX()
                && start.getY() > 0 && end.getY() > 0
                && start.getX() > 0 && end.getX() > 0;
    }

    // fill every position with maximun value
    @Override
    public Integer[][] initializationDistances(String[][] matrix) {
        // initialize distances array filled with infinity
        Integer[][] distances = new Integer[matrix.length][];

        for (Integer i = 0; i < matrix.length; i++) {
            distances[i] = new Integer[matrix[i].length];
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        return distances;
    }

    // store every coordinate found for the path
    @Override
    public List<Coordinate> getCoordinatesPath(String[][] matrix, Integer[][] distances, Coordinate start, Coordinate end) {
        // the start node should get distance 0
        int distance = 0;
        List<Coordinate> currentCoordinate = Arrays.asList(start);

        while (distances[end.getY()][end.getX()] == Integer.MAX_VALUE
                && !currentCoordinate.isEmpty()) {
            List<Coordinate> nextCoordinate = new ArrayList<>();

            // loop over all coords added in previous round
            // set their distance
            //    and add their neighbors to the list for next round
            for (Coordinate coord : currentCoordinate) {
                if (distances[coord.getY()][coord.getX()] == Integer.MAX_VALUE
                        && !matrix[coord.getY()][coord.getX()].equals("M") ) {
                    distances[coord.getY()][coord.getX()] = distance;
                    addNeighbors(coord, nextCoordinate, matrix.length, matrix[0].length);
                }
            }
            // prepare for next round
            currentCoordinate = nextCoordinate;
            distance++;
        }
        return currentCoordinate;
    }

    // get the size path found for solution and the CoordinatesDeque
    @Override
    public Deque<Coordinate> obtainCoordinatesDeque(Integer[][] distances, Coordinate end) {
        Deque<Coordinate> finalPath = new ArrayDeque<>();

        try {
            if (distances[end.getY()][end.getX()] < Integer.MAX_VALUE) {
                Coordinate coord = end;
                finalPath.push(end);
                for (Integer d = distances[end.getY()][end.getX()]-1; d >= 0; d--) {
                    coord = getNeighbor(coord, d, distances);
                    finalPath.push(coord);
                }
            }
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }

        return finalPath;
    }

    // obtains the final path for the solution
    @Override
    public List<List<Integer>> getRecomendedPath(Deque<Coordinate> dequePath) {
        List<List<Integer>> coordinatesPath = new ArrayList<>();

        // extract every value from path
        while (!dequePath.isEmpty()) {

            String actual = dequePath.pop().toString();
            Integer index = actual.indexOf(".");
            // split the response because its string value separate by point(".")
            Integer left  = Integer.parseInt(actual.substring(0, index));
            Integer right  = Integer.parseInt(actual.substring(index + 1, actual.length()));

            // save path ir order from start positio to final position
            coordinatesPath.add(Arrays.asList(left, right));
        }
        return coordinatesPath;
    }

    // add all valid neighbors of a coord to the list
    // where "valid" means: indices inside the maze
    @Override
    public void addNeighbors(Coordinate coord, List<Coordinate> list, Integer maxY, Integer maxX) {
        Integer[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (Integer[] d : ds) {
            Integer y = coord.getY() + d[0];
            Integer x = coord.getX() + d[1];
            if (isValid(y, x, maxY, maxX))
                list.add(new Coordinate(y, x));
        }
    }

    // find the neighbor of a coord having a certain distance from the start
    @Override
    public Coordinate getNeighbor(Coordinate coord, Integer distance, Integer[][] distances) {
        Integer[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (Integer[] d : ds) {
            Integer y = coord.getY() + d[0];
            Integer x = coord.getX() + d[1];
            if (isValid(y, x, distances.length, distances[0].length)
                    && distances[y][x].equals(distance))
                return new Coordinate(y, x);
        }
        return null;
    }

    // check if coordinates are inside the matrix
    @Override
    public boolean isValid(Integer y, Integer x, Integer maxY, Integer maxX) {
        return y >= 0 && y < maxY && x >= 0 && x < maxX;
    }
}