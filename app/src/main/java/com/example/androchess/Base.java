package com.example.androchess;

public class Base extends Unity implements Heal{

    Base(boolean blue){
        super(blue);
        name="Base";
        hpMax=250;
        hpCurrent=hpMax;
        attack=50;//attack pour base : c'est le heal
        armor=25;
        range=2;
        maxMovements=0;
        availableMovements=maxMovements;
        position=new Coordinates(0,0);//faire un setPosition au drag and drop initial
        spriteName="Base.png";//a modifier avec le sprite final
        hasAttacked=false;//has attacked pour base : c'est le heal

    }
    @Override
    public boolean isWeak(Unity unity) {
        return false;
    }
    @Override
    public boolean isStrong(Unity unity) {
        return false;
    }
    @Override
    public void heal(Unity unity) {
        if(this.isInAttackRange(unity)){
            unity.getHeal(this.getAttack());
            hasAttacked=true;
        }
    }
    @Override
    public void attackUnity(Unity unity){
        heal(unity);
    }
}
