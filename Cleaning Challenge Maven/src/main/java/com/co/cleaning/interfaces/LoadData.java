package com.co.cleaning.interfaces;

public interface LoadData {

    String[][] readFile(String fileName);
    String[][] fillMatrix(String[] lines, int maxLength);
    Integer getMaxLength(String[] lines);
}
