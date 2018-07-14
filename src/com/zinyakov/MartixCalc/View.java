package com.zinyakov.MartixCalc;

import com.zinyakov.Models.Matrix;
import com.zinyakov.Models.OperationType;

import java.util.List;

import static com.zinyakov.Constants.HELP_MESSAGE;

public class View {

    private ViewModel viewModel;

    public View(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public String resultViewForArgs(String[] args) {
        Matrix firstMatrix = new Matrix();
        Matrix secondMatrix = new Matrix();
        OperationType operationType = OperationType.NONE;

        try {
            if (args.length >= 1) {
                operationType = OperationType.fromString(args[0]);
            }
            if (args.length >= 2) {
                firstMatrix = viewModel.matrixForArg(args[1]);
            }
            if (args.length >= 3) {
                secondMatrix = viewModel.matrixForArg(args[2]);
            }

            return resultForOperation(operationType, firstMatrix, secondMatrix);
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    private String resultForOperation(OperationType type, Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        switch (type) {
            case ADD:
                Matrix additionResultMatrix = viewModel.additionForMatrixes(firstMatrix, secondMatrix);
                return twoMatrixesResultView(firstMatrix, secondMatrix, additionResultMatrix);
            case MULTIPLY:
                Matrix multiplicationResultMatrix = viewModel.multiplicationForMatrixes(firstMatrix, secondMatrix);
                return twoMatrixesResultView(firstMatrix, secondMatrix, multiplicationResultMatrix);
            case DETERMINE:
                int determinant = viewModel.determinantForMatrix(firstMatrix);
                return singleMatrixValueView(firstMatrix, determinant);
            default:
                return HELP_MESSAGE;
        }
    }

    private String singleMatrixValueView(Matrix matrix, int value) {
        return "matrix" + "\n"
                + matrixView(matrix) + "\n"
                + "result" + "\n"
                + value;
    }

    private String twoMatrixesResultView(Matrix firstMatrix, Matrix secondMatrix, Matrix resultMatrix) {
        return "first matrix" + "\n"
                + matrixView(firstMatrix) + "\n"
                + "second matrix" + "\n"
                + matrixView(secondMatrix) + "\n"
                + "result" + "\n"
                + matrixView(resultMatrix);
    }

    private String matrixView(Matrix matrix) {
        String view = "";
        for (List<Integer> row : matrix.getValue()) {
            for (Integer element : row) {
                view += element.toString() + " ";
            }
            view += "\n";
        }
        return view;
    }

}
