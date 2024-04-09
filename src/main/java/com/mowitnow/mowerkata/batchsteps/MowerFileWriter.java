package com.mowitnow.mowerkata.batchsteps;

import com.mowitnow.mowerkata.model.Mower;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MowerFileWriter implements ItemWriter<Mower> {

    private String outputPath;

    public MowerFileWriter(String path) {
        outputPath = path;
    }


    @Override
    public void write(List<? extends Mower> mowers) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter((new FileWriter(outputPath, true)));
            for (Mower mower : mowers) {
                writer.println(mower.getPosition().getX() + " " +
                        mower.getPosition().getY() + " " + mower.getDirection().getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
