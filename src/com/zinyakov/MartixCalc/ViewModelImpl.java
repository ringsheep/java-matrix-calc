package com.zinyakov.MartixCalc;

import com.zinyakov.Models.Matrix;

import java.util.ArrayList;
import java.util.List;

public class ViewModelImpl implements ViewModel {

    public Matrix additionForMatrixes(Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        if (firstMatrix.value.isEmpty() || secondMatrix.value.isEmpty()) {
            throw new Exception("both matrixs must not be empty");
        }
        if (firstMatrix.value.size() != secondMatrix.value.size()) {
            throw new Exception("first and second matrixes must have same number of rows");
        }
        List<List<Integer>> combinedMatrixes = new ArrayList<>();
        for (int i = 0; i < firstMatrix.value.size(); i++) {
            List<Integer> firstMatrixRow = firstMatrix.value.get(i);
            List<Integer> secondMatrixRow = secondMatrix.value.get(i);

            if (firstMatrixRow.size() != secondMatrixRow.size()) {
                throw new Exception("first and second matrixes must have same number of elements in each row");
            }

            List<Integer> combinedRow = new ArrayList<>(firstMatrixRow);
            for (int j = 0; j < combinedRow.size(); j++) {
                combinedRow.set(j, firstMatrixRow.get(j) + secondMatrixRow.get(j));
            }
            combinedMatrixes.add(combinedRow);
        }
        return new Matrix(combinedMatrixes);
    }

}
