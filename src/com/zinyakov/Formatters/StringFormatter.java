package com.zinyakov.Formatters;

import static com.zinyakov.Constants.*;

class StringFormatter {

    Integer safeIntegerFromString(String string) {
        try {
            return Integer.parseInt(string.trim());
        }
        catch (NumberFormatException exception) {
            return null;
        }
    }

    String removingFirstAndLastCharacter(String string) {
        if (string == null || string.length() <= 1) {
            return "";
        }
        return string.substring(1, lastIndex(string));
    }

    int lastIndex(String string) {
        return string.length() - 1;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    boolean isAnArray(String string) {
        if (isNullOrEmpty(string) || !isValidBracketExpression(string)) {
            return false;
        }
        boolean isValidTopLevelArray = string.charAt(0) == LEFT_BRACKET && string.charAt(lastIndex(string)) == RIGHT_BRACKET;
        boolean isValidLowLevelExpression = isValidBracketExpression(removingFirstAndLastCharacter(string));

        return isValidTopLevelArray && isValidLowLevelExpression;
    }

    private boolean isValidBracketExpression(String string) {
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
