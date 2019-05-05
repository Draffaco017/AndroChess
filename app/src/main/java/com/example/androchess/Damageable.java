package com.example.androchess;

public interface Damageable {
    void getDamages(Unity unity);
    void attackUnity(Unity unity);
    boolean isWeak(Unity unity);
    boolean isStrong(Unity unity);
}
