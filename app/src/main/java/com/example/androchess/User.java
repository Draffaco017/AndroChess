package com.example.androchess;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable{
    private int id;
    private String name;
    private int nbrGamesWon;
    private int nbrGamesPlayed;
    private double ratioGameWon;
    private ArrayList<String> unitiesNames;//ce qu'on va charger avec la db
    private ArrayList<Healable> unities;
    private String password;
    User(int id, String name, int nbrGamesWon, int nbrGamesPlayed, ArrayList<String>unitiesNames, String password){
        this.id=id;
        this.name=name;
        this.nbrGamesWon=nbrGamesWon;
        this.nbrGamesPlayed=nbrGamesPlayed;
        if(nbrGamesPlayed==0){
            this.ratioGameWon=0;
        }
        else{
            this.ratioGameWon=this.nbrGamesWon/this.nbrGamesPlayed*100;

        }
        this.password=password;
        this.unitiesNames=unitiesNames;
        for(String unityName:this.unitiesNames){
            if(unityName.contains("Aircraft")){
                if(unityName.contains("Damage")){
                    this.unities.add(new Aircraft("Damage"));
                }
                else if(unityName.contains("Speed")){
                    this.unities.add(new Aircraft("Speed"));
                }
                else if(unityName.contains("Tank")){
                    this.unities.add(new Aircraft("Tank"));
                }
                else{
                    this.unities.add(new Aircraft());
                }
            }
            else if(unityName.contains("Melee")){
                if(unityName.contains("Damage")){
                    this.unities.add(new Melee("Damage"));
                }
                else if(unityName.contains("Speed")){
                    this.unities.add(new Melee("Speed"));
                }
                else if(unityName.contains("Tank")){
                    this.unities.add(new Melee("Tank"));
                }
                else{
                    this.unities.add(new Melee());
                }

            }
            else if(unityName.contains("Shooter")){
                if(unityName.contains("Damage")){
                    this.unities.add(new Shooter("Damage"));
                }
                else if(unityName.contains("Speed")){
                    this.unities.add(new Shooter("Speed"));
                }
                else if(unityName.contains("Tank")){
                    this.unities.add(new Shooter("Tank"));
                }
                else{
                    this.unities.add(new Shooter());
                }
            }
            else if(unityName.contains("Base")){
                this.unities.add(new Base());
            }
            else{
                System.out.println("Problème critique !");
            }
        }
    }

    public User(String name, String password) {
        this.name = name;
        this.password=password;
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        nbrGamesWon = in.readInt();
        nbrGamesPlayed = in.readInt();
        ratioGameWon = in.readDouble();
        unitiesNames = in.createStringArrayList();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public int getNbrGamesWon() {
        return nbrGamesWon;
    }

    public int getNbrGamesPlayed() {
        return nbrGamesPlayed;
    }

    public double getRatioGameWon() {
        return ratioGameWon;
    }

    public ArrayList<String> getUnitiesNames() {
        return unitiesNames;
    }

    public ArrayList<Healable> getUnities() {
        return unities;
    }

    public String getUnityName(int i){
        return unitiesNames.get(i);
    }
    public Healable getUnity(int i){
        return unities.get(i);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbrGamesWon(int nbrGamesWon) {
        this.nbrGamesWon = nbrGamesWon;
    }

    public void setNbrGamesPlayed(int nbrGamesPlayed) {
        this.nbrGamesPlayed = nbrGamesPlayed;
    }

    public void setRatioGameWon() {
        if(nbrGamesPlayed==0){
            this.ratioGameWon=0;
        }
        else{
            this.ratioGameWon = this.nbrGamesWon/this.nbrGamesPlayed*100;
        }
    }

    public void setUnitiesNames(ArrayList<String> unitiesNames) {
        this.unitiesNames = unitiesNames;
    }

    public void setUnities(ArrayList<Healable> unities) {
        this.unities = unities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(nbrGamesWon);
        parcel.writeInt(nbrGamesPlayed);
        parcel.writeDouble(ratioGameWon);
        parcel.writeStringList(unitiesNames);
    }
}