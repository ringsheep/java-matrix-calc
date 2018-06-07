package com.zinyakov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            System.out.println(result);

            System.out.println("first matrix");
            for (List<Integer> row : firstMatrix) {
                for (Integer value : row) {
                    System.out.println(value);
                }
            }
            System.out.println("second matrix");
            for (List<Integer> row : secondMatrix) {
                for (Integer value : row) {
                    System.out.println(value);
                }
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<List<Integer>> twoDimentionalMatrixForArg(String arg) {
        List<List<Integer>> resultList = new ArrayList<>();
        String[] stringArray = arrayFromString(arg);

        for (String string : stringArray) {
            List<Integer> row = oneDimentionalMatrixForArg(string);
            if (row != null && !row.isEmpty()) {
                resultList.add(row);
            }
        }

        return resultList;
    }

    private static List<Integer> oneDimentionalMatrixForArg(String arg) {
        List<Integer> resultMatrix = new ArrayList<Integer>();
        String[] stringArray = arrayFromString(arg);

        for (String string : stringArray) {
            Integer integerValue = safeIntegerFromString(string);
            if (integerValue != null) {
                resultMatrix.add(integerValue);
            }
        }

        // fallback, если не смогли сделать матрицу из массива, пробуем создать матрицу из аргумента как из отдельного числа
        if (resultMatrix.isEmpty()) {
            Integer integerValue = safeIntegerFromString(arg);
            if (integerValue != null) {
                resultMatrix.add(integerValue);
            }
        }

        return resultMatrix;
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
                return "well done";
            case MULTIPLY:
                return "well done";
            case DETERMINE:
                return "well done";
            default:
                return HELP_MESSAGE;
        }
    }

    private static Integer safeIntegerFromString(String string) {
        try {
            return Integer.parseInt(string.trim());
        }
        catch (NumberFormatException exception) {
            return null;
        }
    }

    private static String[] arrayFromString(String string) {
        if (!isAnArray(string)) {
            return new String[]{};
        }
        List<String> resultList = new ArrayList<String>();
        String arrayContentString = removingFirstAndLastCharacter(string);
        int lastSplitIndex = -1;
        int newSplitIndex = 0;
        boolean isLoopingInInnerArray = false;

        for (int i = 0; i < arrayContentString.length(); i++) {
            char character = arrayContentString.charAt(i);

            if (character == '[') {
                isLoopingInInnerArray = true;
            } else if (character == ']') {
                isLoopingInInnerArray = false;
            } else if (character == ',' && !isLoopingInInnerArray) {
                // чтобы не ломать вложенные массивы, разбиваем содержимое по элементам верхнего уровня по запятой
                newSplitIndex = i;
                resultList.add(arrayContentString.substring(lastSplitIndex + 1, newSplitIndex));
                lastSplitIndex = newSplitIndex;
            } else if (i == lastIndex(arrayContentString)) {
                // так как после последнего элемента нет запятой, берем от места последнего разбиения до конца
                resultList.add(arrayContentString.substring(lastSplitIndex + 1, arrayContentString.length()));
            }
        }

        String[] resultArray = new String[ resultList.size() ];
        return resultList.toArray(resultArray);
    }

    private static boolean isAnArray(String string) {
        if (isNullOrEmpty(string) || !isValidBracketExpression(string)) {
            return false;
        }
        boolean isValidTopLevelArray = string.charAt(0) == '[' && string.charAt(lastIndex(string)) == ']';
        boolean isValidLowLevelExpression = isValidBracketExpression(removingFirstAndLastCharacter(string));

        return isValidTopLevelArray && isValidLowLevelExpression;
    }

    private static boolean isValidBracketExpression(String string) {
        int openCloseBracketCounter = 0;
        for (char character : string.toCharArray()) {
            if (character == '[') {
                openCloseBracketCounter += 1;
            } else if (character == ']') {
                openCloseBracketCounter -= 1;
            }
            if (openCloseBracketCounter < 0) {
                return false;
            }
        }
        return true;
    }

    private static String removingFirstAndLastCharacter(String string) {
        if (string == null || string.length() <= 1) {
            return "";
        }
        return string.substring(1, lastIndex(string));
    }

    private static int lastIndex(String string) {
        return string.length() - 1;
    }

    private static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
