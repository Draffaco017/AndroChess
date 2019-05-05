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
    Melee(String specialisation){
        this();
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
