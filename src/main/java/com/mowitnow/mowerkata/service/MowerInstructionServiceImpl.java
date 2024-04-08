package com.mowitnow.mowerkata.service;

import com.mowitnow.mowerkata.model.Lawn;
import com.mowitnow.mowerkata.model.Mower;
import org.springframework.stereotype.Service;

@Service
public class MowerInstructionServiceImpl implements MowerInstructionService {
    @Override
    public void applyMowerInstructions(Mower mower, Lawn lawn) {
        for (char instruction : mower.getInstructions().toCharArray()) {
            if (instruction == 'G') {
                turnLeft(mower);
            } else if (instruction == 'D') {
                turnRight(mower);
            } else if (instruction == 'A') {
                move(mower, lawn);
            }
        }
    }

    private void turnRight(Mower mower) {

        switch (mower.getDirection()) {
            case 'N':
                mower.setDirection('E');
                break;
            case 'E':
                mower.setDirection('S');
                break;
            case 'S':
                mower.setDirection('W');
                break;
            case 'W':
                mower.setDirection('N');
                break;
        }
    }

    private void turnLeft(Mower mower) {

        switch (mower.getDirection()) {
            case 'N':
                mower.setDirection('W');
                break;
            case 'E':
                mower.setDirection('N');
                break;
            case 'S':
                mower.setDirection('E');
                break;
            case 'W':
                mower.setDirection('S');
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
