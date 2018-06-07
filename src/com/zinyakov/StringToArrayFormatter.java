package com.zinyakov;

import java.util.ArrayList;
import java.util.List;

import static com.zinyakov.StringUtils.*;

public class StringToArrayFormatter {

    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';
    private static final char SEPARATOR = ',';

    public static List<List<Integer>> twoDimentionalArrayForArg(String arg) {
        List<List<Integer>> resultList = new ArrayList<>();
        String[] stringArray = arrayFromString(arg);

        for (String string : stringArray) {
            List<Integer> row = oneDimentionalArrayForArg(string);
            if (row != null && !row.isEmpty()) {
                resultList.add(row);
            }
        }

        return resultList;
    }

    private static List<Integer> oneDimentionalArrayForArg(String arg) {
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

    // "[[1,2,3],4,5]" -> ["[1,2,3]", "4", "5"]
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

            if (character == LEFT_BRACKET) {
                isLoopingInInnerArray = true;
            } else if (character == RIGHT_BRACKET) {
                isLoopingInInnerArray = false;
            } else if (character == SEPARATOR && !isLoopingInInnerArray) {
                // чтобы не ломать вложенные массивы, разбиваем содержимое по элементам верхнего уровня по запятой
                newSplitIndex = i;
                resultList.add(arrayContentString.substring(lastSplitIndex + 1, newSplitIndex));
                lastSplitIndex = newSplitIndex;
            }
            if (i == lastIndex(arrayContentString)) {
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
        boolean isValidTopLevelArray = string.charAt(0) == LEFT_BRACKET && string.charAt(lastIndex(string)) == RIGHT_BRACKET;
        boolean isValidLowLevelExpression = isValidBracketExpression(removingFirstAndLastCharacter(string));

        return isValidTopLevelArray && isValidLowLevelExpression;
    }

    private static boolean isValidBracketExpression(String string) {
        int openCloseBracketCounter = 0;
        for (char character : string.toCharArray()) {
            if (character == LEFT_BRACKET) {
                openCloseBracketCounter += 1;
            } else if (character == RIGHT_BRACKET) {
                openCloseBracketCounter -= 1;
            }
            if (openCloseBracketCounter < 0) {
                return false;
            }
        }
        return true;
    }
}
