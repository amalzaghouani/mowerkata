package com.mowitnow.mowerkata.utils;

import io.micrometer.core.instrument.util.StringUtils;

public class MowerDataValidationImpl implements MowerDataValidation {
    @Override
    public boolean isValidLawnLine(String line) {
        return StringUtils.isNotBlank(line) && line.matches("^[0-9] [0-9]$");
    }

    @Override
    public boolean isValidMowerPositionLine(String line) {
        return StringUtils.isNotBlank(line) && line.matches("^[0-9] [0-9] (N|S|W|E)$");
    }

    @Override
    public boolean isValidMowerInstructionLine(String line) {
        return StringUtils.isNotBlank(line) && line.matches("^[ADG]+$");
    }
}
