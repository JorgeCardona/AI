package com.co.cleaning.useCase;

import com.co.cleaning.interfaces.DataProcessing;
import com.co.cleaning.model.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixDataProccessing implements DataProcessing {

    // found every posible path for cleaning
    @Override
    public List<List<List<Integer>>> getAllPath(String[][] dataFromFile, List<List<Integer>> allCoordinates) {

        MatrixExtractInformation matrixExtractInformation = new MatrixExtractInformation();
        // store every path create for cleaning the matrix
        List<List<List<Integer>>> allPaths = new ArrayList<>();

        // executes the algorithm until does not have elements for cleaning
        while(!allCoordinates.isEmpty()){

            // calculate the path bettween the matrix elements
            List<List<Integer>> joinPath = joinPath(allCoordinates, dataFromFile);

            for(Integer i=0; i <joinPath.size(); i++ ){

                List<Integer> data = joinPath.get(i);
                Integer y = data.get(0);
                Integer x = data.get(1);

                // cleans path on the matrix
                dataFromFile[y][x] = "C";

            }
            // obtain all matrix coordinates for cleaning
            allCoordinates = matrixExtractInformation.obtainPositionsForCleaning(matrixExtractInformation.convertArraytoList(dataFromFile));

            // save every posible path into matrix
            allPaths.add(joinPath);
        }

        // return all paths from solving the cleaning matrix
        return allPaths;
    }

    // return the path in index order, join all the coordinates in way
    @Override
    public List<List<Integer>> joinPath(List<List<Integer>> coordenates, String[][] dataFromFile) {

        List<List<Integer>> finalPath = new ArrayList<>();
        Integer coordinatesSize = coordenates.size();

        if(coordinatesSize == 0 || coordinatesSize == 1)  {

            return coordenates;

        } else {

            finalPath.add(coordenates.get(0));

            for(Integer i = 0; i < coordinatesSize; i++) {

                // obtain the last value on the accumulate list
                List<Integer> lastValue = finalPath.get(finalPath.size() - 1);
                // captura ethe actual valor to compare
                List<Integer> actualValue = coordenates.get(i);

                // search the path between 2 points
                List<List<Integer>> coordinatesPath = getRoute(dataFromFile, lastValue, actualValue);

                // validate if exists route
                boolean existRoute = !coordinatesPath.isEmpty();

                // add values if exists route
                if(existRoute && (actualValue != lastValue) ){

                    coordinatesPath.remove(lastValue);
                    finalPath.addAll(coordinatesPath);
                }

            }
        }

        return finalPath;
    }

    // get tue path  bettwen 2 coordinates
    @Override
    public List<List<Integer>> getRoute(String[][] dataFromFile, List<Integer> actual, List<Integer> next) {

        MatrixDijkstraImplementation matrixDijkstraImplementation = new MatrixDijkstraImplementation();

        return matrixDijkstraImplementation.obtainRoute(dataFromFile, new Coordinate(
                actual.get(0), actual.get(1)), new Coordinate(next.get(0), next.get(1)));
    }

    // obtains the solve solution for cleaning
    @Override
    public List<List<Integer>> getPathSolution(Coordinate startPosition, List<List<List<Integer>>> allPath) {

        List<List<Integer>> finalPath = new ArrayList<>();

        for (List<List<Integer>> path:allPath) {

            // validate if coordinate exists in actual path
            if(existsInPath(path, startPosition)){

                // obtains the position from start position
                Integer position = indexInPath(path, startPosition);

                // add all path to final path
                finalPath = completePath(path, position);
            }
        }
        // delete consecutive element duplicate from final path
        return  removeDuplicateElement(finalPath);
    }

    // validate if coordinate exist in path
    @Override
    public boolean existsInPath(List<List<Integer>> path, Coordinate startPosition) {

        return path.contains(Arrays.asList(startPosition.getY(), startPosition.getX()));
    }

    // obtain index position from coordinate in path
    @Override
    public Integer indexInPath(List<List<Integer>> path, Coordinate startPosition) {

        return path.indexOf(Arrays.asList(startPosition.getY(), startPosition.getX()));
    }

    // obtains the completePath for solution
    @Override
    public List<List<Integer>> completePath(List<List<Integer>> path, Integer position) {

        List<List<Integer>> finalPath = new ArrayList<>();

        //obtain left way
        List<List<Integer>> leftSideWay = leftSideWay(path, position);

        //obtain right way
        List<List<Integer>> rightSideWay = rightSideWay(path, position);

        // add all path to final path
        finalPath.addAll(leftSideWay);
        finalPath.addAll(rightSideWay);

        // return rightside way
        return finalPath;
    }

    // obtains the left side path for solution
    @Override
    public List<List<Integer>> leftSideWay(List<List<Integer>> path, Integer position) {

        List<List<Integer>> leftSideWay = new ArrayList<>();

        //obtain letf side
        List<List<Integer>> leftSide = path.subList(0, position + 1);

        // obtain reverse way from left side
        List<List<Integer>> reverse = leftSide.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(ArrayList::new), lst -> {
                            Collections.reverse(lst);
                            return lst.stream();
                        }
                )).collect(Collectors.toCollection(ArrayList::new));

        leftSideWay.addAll(reverse);
        leftSideWay.addAll(leftSide);

        // return leftside way
        return leftSideWay;
    }

    // obtains the right side path for solution
    @Override
    public List<List<Integer>> rightSideWay(List<List<Integer>> path, Integer position) {
        // return rightside way
        return path.subList(position, path.size());
    }

    // delete consecutive element duplicate from final path
    @Override
    public List<List<Integer>> removeDuplicateElement(List<List<Integer>> elements) {

        List<List<Integer>> cleanList = new ArrayList<>();

        for (List<Integer> values: elements) {

            if (cleanList.isEmpty()){
                cleanList.addAll(Collections.singleton(values));

            } else {

                if(!cleanList.get(cleanList.size() - 1).equals(values)){

                    cleanList.addAll(Collections.singleton(values));
                }
            }
        }

        return cleanList;
    }
}
