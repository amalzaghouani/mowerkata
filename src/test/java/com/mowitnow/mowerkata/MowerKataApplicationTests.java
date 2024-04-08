package com.mowitnow.mowerkata;

import com.mowitnow.mowerkata.exception.InvalidFileFormatException;
import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;
import com.mowitnow.mowerkata.model.MowerData;
import com.mowitnow.mowerkata.model.Position;
import com.mowitnow.mowerkata.batchsteps.MowerFileProcessor;
import com.mowitnow.mowerkata.batchsteps.MowerFileReader;
import com.mowitnow.mowerkata.batchsteps.MowerFileWriter;
import com.mowitnow.mowerkata.service.MowerInstructionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class MowerKataApplicationTests {

    @Test
    void MowerReaderTest() throws Exception {
        MowerFileReader reader = new MowerFileReader("src/test/resources/inputTestData.txt");
        MowerData mowerdata = reader.read();
        Lawn expectedLawn = new Lawn(5, 5);
        Mower expectedMower = new Mower(new Position(1, 2), 'N', "GAGAGAGAA");
        assertEquals(expectedLawn, mowerdata.getLawn());
        assertEquals(expectedMower, mowerdata.getMower());
    }

    @Test
    void MowerReaderThrowsExceptionTest() throws Exception {
        MowerFileReader reader = new MowerFileReader("src/test/resources/invalidInputTestData.txt");
        assertThrows(InvalidFileFormatException.class, () -> reader.read());
    }

    @Test
    void MowerProcessorTest() {
        Mower mower = new Mower(new Position(1, 2), 'N', "GAGAGAGAA");
        MowerData mowerData = new MowerData(new Lawn(5, 5), mower);
        Mower expectedMower = new Mower(new Position(1, 3), 'N', null);
        MowerFileProcessor processor = new MowerFileProcessor();
        Mower actualMower = processor.process(mowerData);
        assertEquals(actualMower.getPosition(), expectedMower.getPosition());
        assertEquals(actualMower.getDirection(), expectedMower.getDirection());
    }

    @Test
    public void MowerWriterTest() throws Exception {
        String outputPath = "src/test/resources/outputTestData.txt";
        File file = new File(outputPath);
        if (file.exists()) {
            file.delete();
        }
        MowerFileWriter writer = new MowerFileWriter(outputPath);
        List<Mower> mowers = new ArrayList();
        mowers.add(new Mower(new Position(1, 2), 'N', null));
        mowers.add(new Mower(new Position(3, 4), 'S', null));
        writer.write(mowers);
        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertEquals(2, lines.size());
        assertEquals("1 2 N", lines.get(0));
        assertEquals("3 4 S", lines.get(1));
    }
}

