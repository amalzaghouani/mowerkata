package com.mowitnow.mowerkata.service;

import com.mowitnow.mowerkata.model.*;
import org.springframework.stereotype.Service;

@Service
public class MowerInstructionServiceImpl implements MowerInstructionService {
    @Override
    public void applyMowerInstructions(MowerData mowerData) {
        Mower mower = mowerData.getMower();
        for (char instruction : mower.getInstructions().toCharArray()) {
            if (instruction == Instruction.LEFT.getValue()) {
                turnLeft(mower);
            } else if (instruction == Instruction.RIGHT.getValue()) {
                turnRight(mower);
            } else if (instruction == Instruction.MOVE.getValue()) {
                move(mower, mowerData.getLawn());
            }
        }
    }

    private void turnRight(Mower mower) {

        switch (mower.getDirection()) {
            case NORTH:
                mower.setDirection(Direction.EAST);
                break;
            case EAST:
                mower.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                mower.setDirection(Direction.WEST);
                break;
            case WEST:
                mower.setDirection(Direction.NORTH);
                break;
        }
    }

    private void turnLeft(Mower mower) {

        switch (mower.getDirection()) {
            case NORTH:
                mower.setDirection(Direction.WEST);
                break;
            case EAST:
                mower.setDirection(Direction.NORTH);
                break;
            case SOUTH:
                mower.setDirection(Direction.EAST);
                break;
            case WEST:
                mower.setDirection(Direction.SOUTH);
                break;
        }
    }


    private void move(Mower mower, Lawn lawn) {
        int y = mower.getPosition().getY();
        int x = mower.getPosition().getX();
        int lawnHeight = lawn.getHeight();
        int lawnWidth = lawn.getWidth();
        switch (mower.getDirection()) {
            case NORTH:
                if (y < lawnHeight)
                    mower.getPosition().setY(y + 1);
                break;
            case EAST:
                if (x < lawnWidth)
                    mower.getPosition().setX(x + 1);
                break;
            case SOUTH:
                if (y > 0)
                    mower.getPosition().setY(y - 1);
                break;
            case WEST:
                if (x > 0)
                    mower.getPosition().setX(x - 1);
                break;
        }
    }


}
