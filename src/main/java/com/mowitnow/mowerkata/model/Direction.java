package com.mowitnow.mowerkata.model;

public enum Direction {
    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');
    private final char value;

    Direction(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static Direction fromValue(char value) throws Exception {
        for (Direction enumItem : Direction.values()) {
            if (enumItem.getValue() == value) {
                return enumItem;
            }
        }
        throw new Exception("Direction value not valid");
    }


}
