package com.mowitnow.mowerkata.utils;

public interface MowerDataValidation {

    boolean isValidLawnLine(String line);

    boolean isValidMowerPositionLine(String line);

    boolean isValidMowerInstructionLine(String line);
}
