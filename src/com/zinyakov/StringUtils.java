package com.zinyakov;

public class StringUtils {

    static Integer safeIntegerFromString(String string) {
        try {
            return Integer.parseInt(string.trim());
        }
        catch (NumberFormatException exception) {
            return null;
        }
    }

    static String removingFirstAndLastCharacter(String string) {
        if (string == null || string.length() <= 1) {
            return "";
        }
        return string.substring(1, lastIndex(string));
    }

    static int lastIndex(String string) {
        return string.length() - 1;
    }

    static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
