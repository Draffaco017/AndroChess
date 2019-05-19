package com.example.androchess;

/**
 * Created by DIPANSH KHANDELWAL on 03-06-2017
 */

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

