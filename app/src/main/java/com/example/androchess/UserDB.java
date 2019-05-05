package com.example.androchess;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDB {
    private static final int VERSION = 1;
    private static final String NAME_BDD = "users.db";
    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COl_WIN ="WIN";
    private static final int NUM_COL_WIN = 2;
    private static final String COL_TOTGAMES = "TOTGAMES";
    private static final int NUM_COL_TOTGAMES = 3;
    private static final String COL_UNIT1 = "UNIT1";
    private static final int NUM_COL_UNIT1 = 4;
    private static final String COL_UNIT2 = "UNIT2";
    private static final int NUM_COL_UNIT2 = 5;
    private static final String COL_UNIT3 = "UNIT3";
    private static final int NUM_COL_UNIT3 = 6;
    private static final String COL_UNIT4 = "UNIT4";
    private static final int NUM_COL_UNIT4 = 7;
    private static final String COL_UNIT5 = "UNIT5";
    private static final int NUM_COL_UNIT5 = 8;
    private static final String COL_UNIT6 = "UNIT6";
    private static final int NUM_COL_UNIT6 = 9;
    private static final String COL_UNIT7 = "UNIT7";
    private static final int NUM_COL_UNIT7 = 10;
    private static final String COL_UNIT8 = "UNIT8";
    private static final int NUM_COL_UNIT8 = 11;
    private static final String COL_UNIT9 = "UNIT9";
    private static final int NUM_COL_UNIT9 = 12;
    private static final String COL_UNIT10 = "UNIT10";
    private static final int NUM_COL_UNIT10 = 13;
    private static final String COL_PSWD = "PSWD";
    private static final int NUM_COL_PSWD = 14;

    private SQLiteDatabase db;
    private UserBaseSQLite users;

    public UserDB(Context context){
        users=new UserBaseSQLite(context, NAME_BDD, null, VERSION);
    }

    public void openForWrite(){
        db=users.getWritableDatabase();
    }
    public void openForRead(){
        db=users.getReadableDatabase();
    }
    public void close(){
        db.close();
    }

    public SQLiteDatabase getDB(){
        return db;
    }

    public long insertUser(User user){
        ContentValues content=new ContentValues();
        content.put(COL_NAME, user.getName());
        content.put(COl_WIN, user.getNbrGamesWon());
        content.put(COL_TOTGAMES, user.getNbrGamesPlayed());
        for(int i=1;i<11;i++){
            String temp="COL_UNIT"+Integer.toString(i);
            String temp2="UNIT"+Integer.toString(i);
            content.put(temp, temp2);
        }
        content.put(COL_PSWD, user.getPassword());
        return db.insert(TABLE_USERS, null, content);
    }
    public int updateUser(int id, User user){
        ContentValues content = new ContentValues();
        content.put(COL_NAME, user.getName());
        content.put(COl_WIN, user.getNbrGamesWon());
        content.put(COL_TOTGAMES, user.getNbrGamesPlayed());
        for(int i=1;i<11;i++){
            String temp="COL_UNIT"+Integer.toString(i);
            String temp2="UNIT"+Integer.toString(i);
            content.put(temp, temp2);
        }
        content.put(COL_PSWD, user.getPassword());
        return db.update(TABLE_USERS, content, COL_ID + " = "+id, null);
    }
    public int removeUser(int id){return db.delete(TABLE_USERS, COL_ID+" = "+Integer.toString(id), null);}

    public User selectUser(int id){
        return null;
    }
    public User getUserMaxID(){
        return null;
    }
    public ArrayList<User>getAllUsers(){
        Cursor c = db.query(TABLE_USERS, new String[]{
                COL_ID, COL_NAME, COL_PSWD, COL_TOTGAMES, COl_WIN, COL_UNIT1,
                COL_UNIT2, COL_UNIT3, COL_UNIT4, COL_UNIT5, COL_UNIT6,
                COL_UNIT7, COL_UNIT8, COL_UNIT9, COL_UNIT10},
                null, null, null, null, COL_ID);
        if(c.getCount()==0){
            c.close();
            return null;
        }
        return null;
       // ArrayList<User> listUser = new ArrayList<~>();
    }
    public boolean checkLoginPswd(){
        return true;
    }
}
