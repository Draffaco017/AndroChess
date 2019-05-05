package com.example.androchess;
public class Melee extends Unity {
    Melee(){
        name="Aircraft";
        hpMax=50;
        hpCurrent=hpMax;
        attack=50;
        armor=50;
        range=1;
        maxMovements=4;
        availableMovements=maxMovements;
        position=new Coordinates(0,0);//faire un setPosition au drag and drop initial
        spriteName="Melee.png";//a modifier avec le sprite final
        hasAttacked=false;
    }

    @Override
    public boolean isWeak(Unity unity) {
        if(unity.getName().contains("Aircraft")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isStrong(Unity unity) {
        if(unity.getName().contains("Shooter")){
            return true;
        }
        return false;
    }
}
