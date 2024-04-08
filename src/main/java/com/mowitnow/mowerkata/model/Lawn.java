package com.mowitnow.mowerkata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Lawn {
    private int width;
    private int height;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lawn lawn = (Lawn) o;
        return width == lawn.width && height == lawn.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
