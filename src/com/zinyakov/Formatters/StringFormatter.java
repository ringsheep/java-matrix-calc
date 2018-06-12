package com.zinyakov.Formatters;

public interface StringFormatter {

    Integer safeIntegerFromString(String string);

    String removingFirstAndLastCharacter(String string);

    int lastIndex(String string);

    boolean isNullOrEmpty(String string);

    boolean isAnArray(String string);

    boolean isValidBracketExpression(String string);

}
