package com.mowitnow.mowerkata.batchsteps;

import com.mowitnow.mowerkata.exception.InvalidFileFormatException;
import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;
import com.mowitnow.mowerkata.model.MowerData;
import com.mowitnow.mowerkata.model.Position;
import com.mowitnow.mowerkata.utils.MowerDataValidation;
import com.mowitnow.mowerkata.utils.MowerDataValidationImpl;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


public class MowerFileReader implements ItemReader<MowerData> {
    private FlatFileItemReader<String> reader;
    private MowerDataValidation moverDataValidator ;
    private boolean isLawnRead;
    private Lawn lawn;
    @Value("#{jobParameters['inputPath']}")
    private String inputPath;

    public MowerFileReader(MowerDataValidation moverDataValidator) {
        this.moverDataValidator = moverDataValidator;
        reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(inputPath));
        reader.setLineMapper(((line, lineNumber) -> line));
    }
      @Override
    public MowerData read() throws Exception {
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
        Mower mower = new Mower(mowerPosition, direction, instructions);
        return new MowerData(lawn, mower);
    }

    private void getLawnData() throws Exception {
        String lawnLine = reader.read();
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
