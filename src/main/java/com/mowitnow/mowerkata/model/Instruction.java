package com.mowitnow.mowerkata.model;

public enum Instruction {
    LEFT('G'), RIGHT('D'), MOVE('A');
    private final char value;

    Instruction(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
