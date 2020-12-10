package com.co.cleaning.interfaces;

import com.co.cleaning.model.Coordinate;

import java.util.List;

public interface DataProcessing {

    List<List<List<Integer>>> getAllPath(String[][] dataFromFile, List<List<Integer>> allCoordinates);
    List<List<Integer>> joinPath(List<List<Integer>> coordenates, String[][] dataFromFile);
    List<List<Integer>> getRoute(String[][] dataFromFile, List<Integer> actual, List<Integer> next);
    List<List<Integer>> getPathSolution(Coordinate startPosition, List<List<List<Integer>>> allPath);
    boolean existsInPath(List<List<Integer>> path, Coordinate startPosition);
    Integer indexInPath(List<List<Integer>> path, Coordinate startPosition);
    List<List<Integer>> completePath(List<List<Integer>> path, Integer position);
    List<List<Integer>> leftSideWay(List<List<Integer>> path, Integer position);
    List<List<Integer>> rightSideWay(List<List<Integer>> path, Integer position);
    List<List<Integer>> removeDuplicateElement(List<List<Integer>> elements);

}
