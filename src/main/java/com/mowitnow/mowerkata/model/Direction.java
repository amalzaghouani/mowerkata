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


}
