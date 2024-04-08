package com.mowitnow.mowerkata.batchsteps;

import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;
import com.mowitnow.mowerkata.model.MowerData;
import com.mowitnow.mowerkata.service.MowerInstructionService;
import com.mowitnow.mowerkata.service.MowerInstructionServiceImpl;
import org.springframework.batch.item.ItemProcessor;

public class MowerFileProcessor implements ItemProcessor<MowerData, Mower> {
    private MowerInstructionService mowerInstructionService = new MowerInstructionServiceImpl();

    public MowerFileProcessor() {
    }

    @Override
    public Mower process(MowerData mowerData) {
        Lawn lawn = mowerData.getLawn();
        Mower mower = mowerData.getMower();
        mowerInstructionService.applyMowerInstructions(mower, lawn);
        return mower;
    }
}
