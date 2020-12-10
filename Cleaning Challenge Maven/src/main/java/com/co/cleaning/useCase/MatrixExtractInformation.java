package com.co.cleaning.useCase;

import com.co.cleaning.interfaces.ExtractInformation;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MatrixExtractInformation implements ExtractInformation {

    // print the matrix of values
    @Override
    public String printMatrix(String[][] dataFromFile) {
        return Arrays.deepToString(dataFromFile);
    }

    // converts the read file to list for proccessing
    @Override
    public List<List<String>> convertArraytoList(String[][] dataFromFile) {
        return  Arrays.stream(dataFromFile)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }

    // get the places when its posible cleans
    @Override
    public List<List<Integer>> obtainPositionsForCleaning(List<List<String>> dataList) {

        List<List<Integer>> position = new ArrayList<>();

        AtomicInteger xPosition = new AtomicInteger();
        AtomicInteger yPosition = new AtomicInteger();
        yPosition.getAndDecrement();

        dataList.stream().forEach(x -> {
            yPosition.getAndIncrement();
            xPosition.set(0);
            x.stream().forEach(
                    y -> {
                        if (y.equals(" ")) {
                            position.add(Arrays.asList(yPosition.get(), xPosition.get()));
                        }
                        xPosition.getAndIncrement();
                    }
            );
        });

        // return list of coordinates when its posible cleans
        return position;
    }

    // validate if perimeter of matrix has wall
    @Override
    public boolean validateMatrixLimits(List<List<String>> dataList) {

        boolean upperAndLowerWall = dataList.get(0).equals(dataList.get(dataList.size() - 1));

        boolean leftAndRightWall = dataList.stream()
                .filter(x -> !x.get(0).equals(x.get(x.size() - 1)))
                .count() < 1;

        return upperAndLowerWall && leftAndRightWall;
    }

    // obtain total value for walls, cleaning spaces, dirties spaces and total spaces from matrix
    @Override
    public Map<String, Long> obtainMatrixValues(List<List<String>> dataList) {

        // save the values that obtains from matrix
        HashMap<String, Long> values = new HashMap<>();

        // obtain the values for every key into Matrix
        Long wallSpaces  = dataList.stream().mapToLong(x -> x.stream().filter(y -> y.equals("M")).count()).sum();
        Long dirtySpaces = dataList.stream().mapToLong(x -> x.stream().filter(y -> y.equals(" ")).count()).sum();
        Long cleanSpaces = dataList.stream().mapToLong(x -> x.stream().filter(y -> y.equals("C")).count()).sum();
        Long totalSpaces = dataList.stream().mapToLong(List::size).sum();

        // add values to hashmap for control the values
        values.put("totalSpaces", totalSpaces);
        values.put("wallSpaces", wallSpaces);
        values.put("cleanSpaces", cleanSpaces);
        values.put("dirtySpaces", dirtySpaces);

        // Order HashMap by Key
        return values.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }
}
