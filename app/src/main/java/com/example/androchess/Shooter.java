package com.example.androchess;

public class Shooter extends Unity{
    Shooter(boolean blue){
        super(blue);
        name="Shooter";
        hpMax=50;
        hpCurrent=hpMax;
        attack=50;
        armor=10;
        range=3;
        maxMovements=2;
        availableMovements=maxMovements;
        position=new Coordinates(0,0);//faire un setPosition au drag and drop initial
        spriteName="Shooter.png";//a modifier avec le sprite final
        hasAttacked=false;
    }
    Shooter(String specialisation, boolean blue){
        this(blue);
        if(specialisation=="Damage"){
            this.setName(this.getName()+" Damage");
            this.setAttack(this.getAttack()*2);
            this.setHpMax(Math.round(this.getHpMax()/2));
            this.setHpCurrent(this.getHpMax());
        }
        else if (specialisation=="Tank"){
            this.setName(this.getName()+" Tank");
            this.setHpMax(this.getHpMax()*2);
            this.setHpCurrent(this.getHpMax());
            this.setArmor(this.getArmor()*2);
            this.setAttack(Math.round(this.getAttack()/2));
        }
        else if(specialisation=="Speed"){
            this.setName(this.getName()+" Speed");
            this.setHpMax(Math.round(this.getHpMax()/2));
            this.setHpCurrent(this.getHpMax());
            this.setMaxMovements(this.getMaxMovements()*2);
            this.setAvailableMovements(this.getMaxMovements());
        }
    }

    @Override
    public boolean isWeak(Unity unity) {
        if(unity.getName().contains("Melee")){
            return true;
        }
        return false;
    }
    @Override
    public boolean isStrong(Unity unity) {
        if(unity.getName().contains("Aircraft")){
            return true;
        }
        return false;
    }
}
