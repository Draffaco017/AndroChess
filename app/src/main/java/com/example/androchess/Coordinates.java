package com.example.androchess;

public class Coordinates {
    private int x;
    private int y;

    Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDistanceTo(Coordinates coordinates){
        return Math.abs(this.x-coordinates.getX())+Math.abs(this.y-coordinates.getY());
    }
}
