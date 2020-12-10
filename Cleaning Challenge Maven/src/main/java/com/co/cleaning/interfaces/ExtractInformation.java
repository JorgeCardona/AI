package com.co.cleaning.interfaces;

import java.util.List;
import java.util.Map;

public interface ExtractInformation {

    String printMatrix (String[][] dataFromFile);
    List<List<String>> convertArraytoList (String[][] dataFromFile);
    List<List<Integer>> obtainPositionsForCleaning(List<List<String>> dataList);
    boolean validateMatrixLimits(List<List<String>> dataList);
    Map<String, Long> obtainMatrixValues(List<List<String>> dataList);

}
