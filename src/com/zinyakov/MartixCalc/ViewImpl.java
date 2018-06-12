package com.zinyakov.MartixCalc;

import com.zinyakov.Formatters.ArgumentMatrixFormatter;
import com.zinyakov.Models.Matrix;
import com.zinyakov.Models.OperationType;

import java.util.List;

import static com.zinyakov.Constants.HELP_MESSAGE;

public class ViewImpl implements View {

    private ViewModel viewModel;
    private ArgumentMatrixFormatter formatter;

    public ViewImpl(ViewModel viewModel, ArgumentMatrixFormatter formatter) {
        this.viewModel = viewModel;
        this.formatter = formatter;
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
                firstMatrix = formatter.twoDimentionalMatrixForArg(args[1]);
            }
            if (args.length >= 3) {
                secondMatrix = formatter.twoDimentionalMatrixForArg(args[2]);
            }

            return resultForOperation(operationType, firstMatrix, secondMatrix);

        } catch(Exception e) {
            return e.getMessage();
        }
    }

    private String resultForOperation(OperationType type, Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        switch (type) {
            case ADD:
                Matrix resultMatrix = viewModel.additionForMatrixes(firstMatrix, secondMatrix);
                return additionResultView(firstMatrix, secondMatrix, resultMatrix);
            case MULTIPLY:
                return "not implemented yet";
            case DETERMINE:
                return "not implemented yet";
            default:
                return HELP_MESSAGE;
        }
    }

    private String additionResultView(Matrix firstMatrix, Matrix secondMatrix, Matrix resultMatrix) {
        String result = "first matrix" + "\n"
                + matrixView(firstMatrix) + "\n"
                + "second matrix" + "\n"
                + matrixView(secondMatrix) + "\n"
                + "result" + "\n"
                + matrixView(resultMatrix);
        return result;
    }

    private String matrixView(Matrix matrix) {
        String view = "";
        for (List<Integer> row : matrix.value) {
            for (Integer element : row) {
                view += element.toString() + " ";
            }
            view += "\n";
        }
        return view;
    }

}
