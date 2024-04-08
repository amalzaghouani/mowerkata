package com.mowitnow.mowerkata.service;

import com.mowitnow.mowerkata.model.Direction;
import com.mowitnow.mowerkata.model.Instruction;
import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;
import org.springframework.stereotype.Service;

@Service
public class MowerInstructionServiceImpl implements MowerInstructionService {
    @Override
    public void applyMowerInstructions(Mower mower, Lawn lawn) {
        for (char instruction : mower.getInstructions().toCharArray()) {
            if (instruction == Instruction.LEFT.getValue()) {
                turnLeft(mower);
            } else if (instruction == Instruction.RIGHT.getValue()) {
                turnRight(mower);
            } else if (instruction == Instruction.MOVE.getValue()) {
                move(mower, lawn);
            }
        }
    }

    private void turnRight(Mower mower) {

        switch (mower.getDirection()) {
            case 'N':
                mower.setDirection(Direction.EAST.getValue());
                break;
            case 'E':
                mower.setDirection(Direction.SOUTH.getValue());
                break;
            case 'S':
                mower.setDirection(Direction.WEST.getValue());
                break;
            case 'W':
                mower.setDirection(Direction.NORTH.getValue());
                break;
        }
    }

    private void turnLeft(Mower mower) {

        switch (mower.getDirection()) {
            case 'N':
                mower.setDirection(Direction.WEST.getValue());
                break;
            case 'E':
                mower.setDirection(Direction.NORTH.getValue());
                break;
            case 'S':
                mower.setDirection(Direction.EAST.getValue());
                break;
            case 'W':
                mower.setDirection(Direction.SOUTH.getValue());
                break;
        }
    }


    private void move(Mower mower, Lawn lawn) {
        int y = mower.getPosition().getY();
        int x = mower.getPosition().getX();
        int lawnHeight = lawn.getHeight();
        int lawnWidth = lawn.getWidth();
        switch (mower.getDirection()) {
            case 'N':
                if (y < lawnHeight)
                    mower.getPosition().setY(y + 1);
                break;
            case 'E':
                if (x < lawnWidth)
                    mower.getPosition().setX(x + 1);
                break;
            case 'S':
                if (y > 0)
                    mower.getPosition().setY(y - 1);
                break;
            case 'W':
                if (x > 0)
                    mower.getPosition().setX(x - 1);
                break;
        }
    }


}
