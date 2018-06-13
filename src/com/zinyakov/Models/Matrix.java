package com.zinyakov.Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Matrix {

    private List<List<Integer>> value;

    public List<List<Integer>> getValue() {
        return value;
    }

    public Matrix() {
        this(0, 0 );
    }

    public Matrix(int numberOfRows, int numberOfColumns) {
        value = new ArrayList<>();

        for (int i = 0; i < numberOfRows; i++) {
            List<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < numberOfColumns; j++) {
                newRow.add(j, 0);
            }
            value.add(i, newRow);
        }
    }

    public Matrix(List<List<Integer>> array) {
        int numberOfRows = array.size();
        int numberOfColumns = array.stream().max(Comparator.comparing(List::size)).get().size();
        Matrix newMatrix = new Matrix(numberOfRows, numberOfColumns);

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                newMatrix.set(i, j, array.get(i).get(j));
            }
        }
        this.value = newMatrix.value;
    }

    public void set(int rowNumber, int columnNumber, Integer newValue) {
        List<Integer> updatedRow = value.get(rowNumber);
        updatedRow.set(columnNumber, newValue);
        value.set(rowNumber, updatedRow);
    }

    public List<Integer> getRow(int rowNumber) {
        return getValue().get(rowNumber);
    }

    public List<Integer> getColumn(int columnNumber) {
        List<Integer> column = new ArrayList<>();

        for (List<Integer> row : getValue()) {
            column.add(row.get(columnNumber));
        }

        return column;
    }

    public int numberOfRows() {
        return value.size();
    }

    public int numberOfColumns() {
        if (value.isEmpty()) {
            return 0;
        }
        return value.get(0).size();
    }

}
