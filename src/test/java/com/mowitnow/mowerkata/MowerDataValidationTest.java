package com.mowitnow.mowerkata;

import com.mowitnow.mowerkata.utils.MowerDataValidation;
import com.mowitnow.mowerkata.utils.MowerDataValidationImpl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerDataValidationTest {
    MowerDataValidation mowerDataValidator = new MowerDataValidationImpl();

    @Test
    void isValidLawnLineTest() {
        Map<String, Boolean> testCases = getLawnTestCases();
        for (Map.Entry<String, Boolean> testCase : testCases.entrySet()
        ) {
            assertEquals(testCase.getValue(), mowerDataValidator.isValidLawnLine(testCase.getKey()));
        }
    }

    private Map<String, Boolean> getLawnTestCases() {
        Map<String, Boolean> testCases = new HashMap<>();
        testCases.put("5 5", true);
        testCases.put("5 7", true);
        testCases.put("5", false);
        testCases.put("toto", false);
        testCases.put("", false);
        testCases.put(null, false);
        return testCases;
    }

    private Map<String, Boolean> getMowerInstructionTestCases() {
        Map<String, Boolean> testCases = new HashMap<>();
        testCases.put("AGDGDG", true);
        testCases.put("GADGAGD", true);
        testCases.put("5", false);
        testCases.put("notavalidinstuction", false);
        testCases.put("", false);
        testCases.put(null, false);
        return testCases;
    }

    private Map<String, Boolean> getMowerPositionTestCases() {
        Map<String, Boolean> testCases = new HashMap<>();
        testCases.put("5 5 N", true);
        testCases.put("5 7 S", true);
        testCases.put("5", false);
        testCases.put("notvalid", false);
        testCases.put("4 4", false);
        testCases.put("44Q", false);
        testCases.put("", false);
        testCases.put(null, false);
        return testCases;
    }

    @Test
    void isValidPositionLineTest() {
        Map<String, Boolean> testCases = getMowerPositionTestCases();
        for (Map.Entry<String, Boolean> testCase : testCases.entrySet()
        ) {
            assertEquals(testCase.getValue(), mowerDataValidator.isValidMowerPositionLine(testCase.getKey()));
        }

    }

    @Test
    void isValidInstructionLineTest() {
        Map<String, Boolean> testCases = getMowerInstructionTestCases();
        for (Map.Entry<String, Boolean> testCase : testCases.entrySet()
        ) {
            assertEquals(testCase.getValue(), mowerDataValidator.isValidMowerInstructionLine(testCase.getKey()));
        }

    }
}
