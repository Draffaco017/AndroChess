package com.example.androchess;

import android.util.Log;

import java.util.ArrayList;

public abstract class Unity implements Movable,Damageable, Healable{
    protected String name;
    protected int hpMax;
    protected int hpCurrent;
    protected int attack;
    protected int armor;
    protected int range;
    protected int maxMovements;
    protected int availableMovements;
    protected Coordinates position;
    protected String spriteName;
    protected boolean hasAttacked = false;
    protected boolean blue;
    protected boolean isAlive = true;
    protected boolean hasMoved = false;

    Unity(boolean blue){
        this.blue = blue;
    }
    //getter
    public String getName() {
        return name;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHpCurrent() {
        return hpCurrent;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getRange() {
        return range;
    }

    public Coordinates getPosition() {
        return position;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public boolean HasAttacked() {
        return hasAttacked;
    }

    //override
    //movable
    @Override
    public int getMaxMovements() {
        return maxMovements;
    }

    @Override
    public int getAvailableMovements() {
        return availableMovements;
    }

    @Override
    public void resetAvailableMovements() {
        availableMovements=maxMovements;
    }

    @Override
    public void move() {

    }
    //damageable
    @Override
    public void getDamages(Unity unity) {
        double bonus=1;
        if(this.isWeak(unity)){
            bonus=2;
        }
        else if(this.isStrong(unity)){
            bonus=0.5;
        }
        int damage=(int) Math.round(unity.getAttack()*bonus-this.armor);
        if(damage<0){
            damage=0;
        }
        this.hpCurrent-=damage;
        Log.d("shotTest", "getDamages: "+this.hpCurrent);
        if(hpCurrent<=0){
            hpCurrent=0;//voir comment détruire l'unité à partir de cette condition
            isAlive = false;
        }
    }
    @Override
    public void attackUnity(Unity unity) {
        if(this.isInAttackRange(unity)){
            unity.getDamages(this);
            hasAttacked=true;
        }
    }

    //healable
    @Override
    public void getHeal(int value) {
        //heal.heal(this);
        this.hpCurrent+=value;
        if(this.hpCurrent>=this.hpMax){
            hpCurrent=hpMax;
        }
    }

    //other Methods
    public boolean isInAttackRange(Unity unity){
        if(this.position.getDistanceTo(unity.getPosition())<=this.range){
            return true;
        }
        return false;
    }
    public boolean isInMovementRange(Coordinates coordinates){
        if(this.position.getDistanceTo(coordinates)<=this.availableMovements){
            return true;
        }
        return false;
    }

    //setter

    public void setName(String name) {
        this.name = name;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public void setHpCurrent(int hpCurrent) {
        this.hpCurrent = hpCurrent;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setMaxMovements(int maxMovements) {
        this.maxMovements = maxMovements;
    }

    public void setAvailableMovements(int availableMovements) {
        this.availableMovements = availableMovements;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
    public void resetHasAttacked(){
        this.setHasAttacked(false);
    }
    public void resetHasMoved(){this.hasMoved=false;}
    public void reset(){
        this.resetAvailableMovements();
        this.resetHasAttacked();
        this.resetHasMoved();
    }
    public boolean isBlue(){return blue;}

    public ArrayList<Coordinates> AllowedMoves(Coordinates coordinates , Position[][] board){
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();
        Coordinates c;

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(coordinates.getDistanceTo(new Coordinates(i, j))<= maxMovements){
                    c=new Coordinates(i , j);
                    allowedMoves.add(c);
                }
            }
        }
        return allowedMoves;
    }

    public ArrayList<Coordinates> AllowedShoot(Coordinates coordinates , Position[][] board){
        ArrayList<Coordinates> allowedShoot = new ArrayList<>();
        allowedShoot.clear();
        Coordinates c;

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(coordinates.getDistanceTo(new Coordinates(i, j))<= range){
                    c=new Coordinates(i , j);
                    allowedShoot.add(c);
                }
            }
        }
        return allowedShoot;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public boolean getHasAttacked(){
        return hasAttacked;
    }

    public boolean getHasMoved(){
        return hasMoved;
    }

    public void setHasMoved(){
        hasMoved = true;
    }
}
