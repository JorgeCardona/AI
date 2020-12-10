package com.co.cleaning.useCase;

import com.co.cleaning.interfaces.LoadData;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatrixLoadData implements LoadData {

    private static final Logger LOG = LoggerFactory
            .getLogger(MatrixLoadData.class);

    @Override
    public String[][] readFile(String fileName) {

        String[][] matrix = null;

        try {
            String txt =
                    IOUtils.toString(MatrixLoadData.class
                            .getResourceAsStream(fileName));
            if (txt != null) {
                String[] lines = txt.split("\n");
                int maxLength = getMaxLength(lines);

                matrix = fillMatrix(lines, maxLength);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return matrix;
    }

    @Override
    public Integer getMaxLength(String[] lines) {

        int maxLength = 0;
        for (int i = 0; i < lines.length; i++) {
            maxLength = Math.max(maxLength, lines[i].length());
        }
        return maxLength;
    }

    @Override
    public String[][] fillMatrix(String[] lines, int maxLength) {

        String[][] matrix = new String[lines.length][maxLength];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < maxLength; j++) {

                if (j < lines[i].length()) {
                    matrix[i][j] = String.valueOf(lines[i].charAt(j));
                } else {
                    matrix[i][j] = " ";
                }
            }
        }
        return matrix;
    }
}