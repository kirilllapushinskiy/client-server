package dev.kirilllapushinskiy.core;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Coordinates {
    private Long x;
    private int y;

    public Coordinates(Long x, int y) {
        this.x = x;
        this.y = y;
    }

    @JsonCreator
    public Coordinates() {
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }
}