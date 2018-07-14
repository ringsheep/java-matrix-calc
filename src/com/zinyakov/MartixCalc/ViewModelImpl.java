package com.zinyakov.MartixCalc;

import com.zinyakov.Formatters.ArgumentArrayFormatter;
import com.zinyakov.Models.Matrix;

import java.util.ArrayList;
import java.util.List;

public class ViewModelImpl implements ViewModel {

    private ArgumentArrayFormatter formatter;

    public ViewModelImpl(ArgumentArrayFormatter formatter) {
        this.formatter = formatter;
    }

    public Matrix matrixForArg(String arg) {
        List<List<Integer>> matrixArray = formatter.twoDimentionalArrayForArg(arg);
        return new Matrix(matrixArray);
    }

    public Matrix additionForMatrixes(Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        if (firstMatrix.getValue().isEmpty() || secondMatrix.getValue().isEmpty()) {
            throw new Exception("both matrixs must not be empty");
        }
        if (firstMatrix.getValue().size() != secondMatrix.getValue().size()) {
            throw new Exception("first and second matrixes must have same number of rows");
        }
        List<List<Integer>> combinedMatrixes = new ArrayList<>();
        for (int i = 0; i < firstMatrix.getValue().size(); i++) {
            List<Integer> firstMatrixRow = firstMatrix.getValue().get(i);
            List<Integer> secondMatrixRow = secondMatrix.getValue().get(i);

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

    public Matrix multiplicationForMatrixes(Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        if (firstMatrix.numberOfColumns() != secondMatrix.numberOfRows()) {
            throw new Exception("number of columns in first matrix must be equal to number of rows in second one");
        }

        Matrix resultMatrix = new Matrix(firstMatrix.numberOfRows(), secondMatrix.numberOfColumns());
        for (int i = 0; i < firstMatrix.numberOfRows(); i++) {
            for (int j = 0; j < secondMatrix.numberOfColumns(); j++) {
                List<Integer> firstMatrixRow = firstMatrix.getRow(i);
                List<Integer> secondMatrixColumn = secondMatrix.getColumn(j);
                List<Integer> resultArray = new ArrayList<>(firstMatrixRow);

                int sum = resultArray.stream().map(value -> {
                    int index = resultArray.indexOf(value);
                    return firstMatrixRow.get(index) * secondMatrixColumn.get(index);
                }).mapToInt(Number::intValue).sum();
                resultMatrix.set(i, j, sum);
            }
        }

        return resultMatrix;
    }

    public int determinantForMatrix(Matrix matrix) throws Exception {
        if (matrix.numberOfRows() != matrix.numberOfColumns()) {
            throw new Exception("you can only calculate determinant of square matrix");
        }
        if (matrix.numberOfRows() == 1) {
            return matrix.getRow(0).get(0);
        }
        if (matrix.numberOfRows() == 2) {
            // det[[1,2],[3,4]] = 1*4 - 2*3
            return matrix.getRow(0).get(0) * matrix.getRow(1).get(1)
                    - matrix.getRow(0).get(1) * matrix.getRow(1).get(0);
        }

        int determinant = 0;
        for (int i = 0; i < matrix.numberOfRows(); i++) {
            Integer currentElement = matrix.getRow(0).get(i);
            Matrix minor = minorForMatrix(matrix, 0, i);

            determinant += Math.pow(-1, 2+i) * currentElement * determinantForMatrix(minor);
        }
        return determinant;
    }

    private Matrix minorForMatrix(Matrix matrix, int rowNumber, int columnNumber) {
        // десериализуем и сериализуем обратно массив, чтобы сделать глубокую копию вложенных массивов
        List<List<Integer>> resultArray = new Matrix(matrix.getValue()).getValue();
        resultArray.remove(rowNumber);
        for (List<Integer> row : resultArray) {
            row.remove(columnNumber);
        }
        return new Matrix(resultArray);
    }

}
