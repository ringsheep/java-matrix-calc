package com.zinyakov;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

    public static String additionForMatrixes(List<List<Integer>> firstMatrix, List<List<Integer>> secondMatrix) {
        if (firstMatrix.size() != secondMatrix.size()) {
            return "first and second matrixes must have same number of rows";
        }
        List<List<Integer>> combinedMatrixes = new ArrayList<>();
        for (int i = 0; i < firstMatrix.size(); i++) {
            List<Integer> firstMatrixRow = firstMatrix.get(i);
            List<Integer> secondMatrixRow = secondMatrix.get(i);

            if (firstMatrixRow.size() != secondMatrixRow.size()) {
                return "first and second matrixes must have same number of elements in each row";
            }

            List<Integer> combinedRow = new ArrayList<>(firstMatrixRow);
            for (int j = 0; j < combinedRow.size(); j++) {
                combinedRow.set(j, firstMatrixRow.get(j) + secondMatrixRow.get(j));
            }
            combinedMatrixes.add(combinedRow);
        }
        return matrixView(combinedMatrixes);
    }

    public static String matrixView(List<List<Integer>> matrix) {
        String view = "";
        for (List<Integer> row : matrix) {
            for (Integer element : row) {
                view += element.toString() + " ";
            }
            view += "\n";
        }
        return view;
    }
}
