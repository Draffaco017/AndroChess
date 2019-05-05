package com.example.androchess;

public class Aircraft extends Unity {
    Aircraft(){
        name="Aircraft";
        hpMax=25;
        hpCurrent=hpMax;
        attack=50;
        armor=25;
        range=2;
        maxMovements=2;
        availableMovements=maxMovements;
        position=new Coordinates(0,0);//faire un setPosition au drag and drop initial
        spriteName="Aircraft.png";//a modifier avec le sprite final
        hasAttacked=false;
    }

    @Override
    public boolean isWeak(Unity unity) {
        if(unity.getName().contains("Shooter")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isStrong(Unity unity) {
        if(unity.getName().contains("Melee")){
            return true;
        }
        return false;
    }
}
