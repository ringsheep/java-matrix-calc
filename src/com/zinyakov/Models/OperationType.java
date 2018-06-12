package com.zinyakov.Models;

public enum OperationType {

    ADD("add"),
    MULTIPLY("multiply"),
    DETERMINE("determine"),
    NONE(null);

    private String textValue;

    OperationType(String textValue) {
        this.textValue = textValue;
    }

    public static OperationType fromString(String text) {
        for (OperationType b : OperationType.values()) {
            if (b.textValue.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return NONE;
    }

}
