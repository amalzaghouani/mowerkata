package com.mowitnow.mowerkata.batchsteps;

import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;
import com.mowitnow.mowerkata.model.MowerData;
import com.mowitnow.mowerkata.service.MowerInstructionService;
import org.springframework.batch.item.ItemProcessor;

public class MowerFileProcessor implements ItemProcessor<MowerData, Mower> {
    private MowerInstructionService mowerInstructionService;

    public MowerFileProcessor(MowerInstructionService mowerInstructionService) {
        this.mowerInstructionService = mowerInstructionService;
    }

    @Override
    public Mower process(MowerData mowerData) {
        Lawn lawn = mowerData.getLawn();
        Mower mower = mowerData.getMower();
        mowerInstructionService.applyMowerInstructions(new MowerData(lawn, mower));
        return mower;
    }
}
