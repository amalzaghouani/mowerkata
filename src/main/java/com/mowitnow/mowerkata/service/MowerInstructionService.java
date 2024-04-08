package com.mowitnow.mowerkata.service;

import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;


public interface MowerInstructionService {
    void applyMowerInstructions(Mower mower, Lawn lawn);
}
