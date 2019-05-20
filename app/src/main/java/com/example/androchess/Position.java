package com.example.androchess;

public class Position {
    private Unity unity;


    Position(Unity unity ) {
        this.unity = unity;
    }

    public Unity getUnity() {
        return unity;

    }
    void setPiece(Unity unity) {
        this.unity = unity;
    }
}

