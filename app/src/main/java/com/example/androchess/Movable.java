package com.example.androchess;

public interface Movable {
    void move();
    int getMaxMovements();
    int getAvailableMovements();
    void resetAvailableMovements();
}
