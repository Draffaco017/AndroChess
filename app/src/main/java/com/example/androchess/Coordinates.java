package com.example.androchess;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }
    void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
    public int getDistanceTo(Coordinates coordinates){
        return Math.abs(this.x-coordinates.getX())+Math.abs(this.y-coordinates.getY());
    }
}
