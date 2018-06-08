package com.zinyakov;

import java.util.ArrayList;
import java.util.List;
import static com.zinyakov.MatrixUtils.*;

public class Main {

    private static final String HELP_MESSAGE =
            "available commands:\n"
            + "add [1,2,3] [[4,5],6]\n"
            + "multiply [1,2,3] [[4,5],6]\n"
            + "determine [1,2,3]\n";

    private enum OperationType {
        ADD, MULTIPLY, DETERMINE, NONE
    }

    public static void main(String[] args) {
        List<List<Integer>> firstMatrix = new ArrayList<>();
        List<List<Integer>> secondMatrix = new ArrayList<>();
        OperationType operationType = OperationType.NONE;

        try {
            if (args.length >= 1) {
                operationType = operationTypeForArg(args[0]);
            }
            if (args.length >= 2) {
                firstMatrix = twoDimentionalMatrixForArg(args[1]);
            }
            if (args.length >= 3) {
                secondMatrix = twoDimentionalMatrixForArg(args[2]);
            }

            String result = resultForOperation(operationType, firstMatrix, secondMatrix);
            System.out.println(result);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static OperationType operationTypeForArg(String arg) {
        switch (arg) {
            case "add":
                return OperationType.ADD;
            case "multiply":
                return OperationType.MULTIPLY;
            case "determine":
                return OperationType.DETERMINE;
            default:
                return OperationType.NONE;
        }
    }

    private static String resultForOperation(OperationType type, List<List<Integer>> firstMatrix, List<List<Integer>> secondMatrix) {
        switch (type) {
            case ADD:
                return additionResult(firstMatrix, secondMatrix);
            case MULTIPLY:
                return "not implemented yet";
            case DETERMINE:
                return "not implemented yet";
            default:
                return HELP_MESSAGE;
        }
    }

    private static String additionResult(List<List<Integer>> firstMatrix, List<List<Integer>> secondMatrix) {
        String result = "first matrix" + "\n"
                + matrixView(firstMatrix) + "\n"
                + "second matrix" + "\n"
                + matrixView(secondMatrix) + "\n"
                + "result" + "\n"
                + additionForMatrixes(firstMatrix, secondMatrix);
        return result;
    }

}
