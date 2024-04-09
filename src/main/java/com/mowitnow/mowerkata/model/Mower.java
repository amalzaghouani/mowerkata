package com.mowitnow.mowerkata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Mower {
    private Position position;
    private Direction direction;
    private String instructions;

    public Mower(Position mowerPosition, Direction direction, String instructions) {
        this.position = mowerPosition;
        this.direction = direction;
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mower mower = (Mower) o;
        return direction == mower.direction && Objects.equals(position, mower.position) && Objects.equals(instructions, mower.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction, instructions);
    }
}
