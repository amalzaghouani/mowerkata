package com.mowitnow.mowerkata.model;

import lombok.Data;

@Data
public class MowerData {
    private Lawn lawn;
    private Mower mower;

    public MowerData(Lawn lawn, Mower mower) {
        this.lawn = lawn;
        this.mower = mower;
    }
}
