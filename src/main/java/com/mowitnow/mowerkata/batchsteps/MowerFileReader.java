package com.mowitnow.mowerkata.batchsteps;

import com.mowitnow.mowerkata.exception.InvalidFileFormatException;
import com.mowitnow.mowerkata.model.*;
import com.mowitnow.mowerkata.utils.MowerDataValidation;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;

public class MowerFileReader implements ItemReader<MowerData> {
    private FlatFileItemReader<String> reader;
    private MowerDataValidation moverDataValidator;
    private boolean isLawnRead;
    private Lawn lawn;

    public MowerFileReader(MowerDataValidation moverDataValidator, String inputFilePath) {
        this.moverDataValidator = moverDataValidator;
        reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(inputFilePath));
        reader.setLineMapper(((line, lineNumber) -> line));
    }

    @Override
    public MowerData read() throws Exception {
        try {
            reader.open(new ExecutionContext());
            if (!isLawnRead) {
                getLawnData();
            }
            String line = reader.read();
            if (line != null) {
                return getMowerData(line);
            } else {
                reader.close();
                return null;
            }
        } catch (Exception e) {
            if (reader != null) {
                reader.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    private MowerData getMowerData(String line) throws Exception {
        if (!moverDataValidator.isValidMowerPositionLine(line)) {
            throw new InvalidFileFormatException("Mower Position line is not valid");
        }
        String[] mowerInitialPosition = line.split(" ");
        int x = Integer.parseInt(mowerInitialPosition[0]);
        int y = Integer.parseInt(mowerInitialPosition[1]);
        Position mowerPosition = new Position(x, y);
        char direction = mowerInitialPosition[2].charAt(0);
        String instructions = reader.read();
        if (!moverDataValidator.isValidMowerInstructionLine(instructions)) {
            throw new InvalidFileFormatException("Mower Instructions line is not valid");
        }

        Mower mower = new Mower(mowerPosition, Direction.fromValue(direction), instructions);
        return new MowerData(lawn, mower);
    }

    private void getLawnData() throws Exception {
        String lawnLine = reader.read();
        if (lawnLine == null) {
            throw new InvalidFileFormatException("File is empty");
        }
        if (!moverDataValidator.isValidLawnLine(lawnLine)) {
            throw new InvalidFileFormatException("Lawn line is not valid");
        }
        String[] dimensions = lawnLine.split(" ");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);
        lawn = new Lawn(width, height);
        isLawnRead = true;
    }
}
