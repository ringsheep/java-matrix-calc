package com.zinyakov;

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
        try {
            OperationType operationType = operationTypeForArg(args[0]);
            // через list, так как было жутко лень преобразовывать двумерный list в двумерный массив
            List<List<Integer>> firstMatrix = twoDimentionalMatrixForArg(args[1]);
            List<List<Integer>> secondMatrix = twoDimentionalMatrixForArg(args[2]);
            String result = resultForOperation(operationType, firstMatrix, secondMatrix);

            System.out.println("first matrix");
            System.out.println(matrixView(firstMatrix));
            System.out.println("second matrix");
            System.out.println(matrixView(secondMatrix));
            System.out.println("result");
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
                return additionForMatrixes(firstMatrix, secondMatrix);
            case MULTIPLY:
                return "not implemented yet";
            case DETERMINE:
                return "not implemented yet";
            default:
                return HELP_MESSAGE;
        }
    }

}
