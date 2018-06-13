package com.zinyakov.Formatters;

import java.util.ArrayList;
import java.util.List;

import static com.zinyakov.Constants.*;

public class ArgumentArrayFormatterImpl implements ArgumentArrayFormatter {

    StringFormatter formatter;

    public ArgumentArrayFormatterImpl(StringFormatter formatter) {
        this.formatter = formatter;
    }

    public List<List<Integer>> twoDimentionalArrayForArg(String arg) {
        List<List<Integer>> resultList = new ArrayList<>();
        String[] stringArray = arrayFromString(arg);
        if (stringArray.length == 0) {
            return new ArrayList<>();
        }

        for (String string : stringArray) {
            List<Integer> row = oneDimentionalArrayForArg(string);
            if (row != null && !row.isEmpty()) {
                resultList.add(row);
            }
        }

        return resultList;
    }

    private List<Integer> oneDimentionalArrayForArg(String arg) {
        List<Integer> resultMatrix = new ArrayList<Integer>();
        String[] stringArray = arrayFromString(arg);

        for (String string : stringArray) {
            Integer integerValue = formatter.safeIntegerFromString(string);
            if (integerValue != null) {
                resultMatrix.add(integerValue);
            }
        }

        // fallback, если не смогли сделать матрицу из массива, пробуем создать матрицу из аргумента как из отдельного числа
        if (resultMatrix.isEmpty()) {
            Integer integerValue = formatter.safeIntegerFromString(arg);
            if (integerValue != null) {
                resultMatrix.add(integerValue);
            }
        }

        return resultMatrix;
    }

    // "[[1,2,3],4,5]" -> ["[1,2,3]", "4", "5"]
    private String[] arrayFromString(String string) {
        if (!formatter.isAnArray(string)) {
            return new String[]{};
        }
        List<String> resultList = new ArrayList<String>();
        String arrayContentString = formatter.removingFirstAndLastCharacter(string);
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
            if (i == formatter.lastIndex(arrayContentString)) {
                // так как после последнего элемента нет запятой, берем от места последнего разбиения до конца
                resultList.add(arrayContentString.substring(lastSplitIndex + 1, arrayContentString.length()));
            }
        }

        String[] resultArray = new String[ resultList.size() ];
        return resultList.toArray(resultArray);
    }

}
