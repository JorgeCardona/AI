package com.co.cleaning.model;

public class Coordinate {

    private Integer y;
    private Integer x;

    public Integer getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Coordinate(Integer y, Integer x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return y + "." + x;
    }
}