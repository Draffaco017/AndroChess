package com.example.androchess;

public class Aircraft extends Unity {
    Aircraft(boolean blue){
        super(blue);
        name="Aircraft";
        hpMax=25;
        hpCurrent=hpMax;
        attack=50;
        armor=20;
        range=2;
        maxMovements=2;
        availableMovements=maxMovements;
        position=new Coordinates(0,0);//faire un setPosition au drag and drop initial
        spriteName="Aircraft.png";//a modifier avec le sprite final
        hasAttacked=false;
        this.blue=blue;
    }
    Aircraft(String specialisation, boolean blue){
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
