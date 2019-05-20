package com.example.androchess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBaseSQLite extends SQLiteOpenHelper {
    private static final String TABLE_USERS = "table_users";
    private static final String COL_PSWD = "PSWD";
    private static final String COL_NAME = "NAME";
    private static final String COL_WIN ="WIN";
    private static final String COL_TOTGAMES = "TOTGAMES";
    private static final String COL_UNIT1 = "UNIT1";
    private static final String COL_UNIT2 = "UNIT2";
    private static final String COL_UNIT3 = "UNIT3";
    private static final String COL_UNIT4 = "UNIT4";
    private static final String COL_UNIT5 = "UNIT5";
    private static final String COL_UNIT6 = "UNIT6";
    private static final String COL_UNIT7 = "UNIT7";
    private static final String COL_UNIT8 = "UNIT8";
    private static final String COL_UNIT9 = "UNIT9";
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS "+TABLE_USERS+" ("+
            COL_PSWD+" TEXT NOT NULL, "+ COL_NAME +" TEXT PRIMARY KEY NOT NULL, "+
            COL_WIN+" INTEGER NOT NULL DEFAULT 0, "+COL_TOTGAMES+" INTEGER NOT NULL DEFAULT 0, "+
            COL_UNIT1+" TEXT NOT NULL, "+ COL_UNIT2+" TEXT NOT NULL, "+
            COL_UNIT3+" TEXT NOT NULL, "+ COL_UNIT4+" TEXT NOT NULL, "+
            COL_UNIT5+" TEXT NOT NULL, "+ COL_UNIT6+" TEXT NOT NULL, "+
            COL_UNIT7+" TEXT NOT NULL, "+ COL_UNIT8+" TEXT NOT NULL, "+
            COL_UNIT9+" TEXT NOT NULL);";
    public UserBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
        db.execSQL("INSERT INTO "+TABLE_USERS+" ("+COL_PSWD+", "+
                COL_NAME+", "+COL_WIN+", "+COL_TOTGAMES+", "+
                COL_UNIT1+", "+COL_UNIT2+", "+COL_UNIT3+", "+
                COL_UNIT4+", "+COL_UNIT5+", "+COL_UNIT6+", "+
                COL_UNIT7+", "+COL_UNIT8+", "+COL_UNIT9+") VALUES ('"+
                "player1"+"', '"+"player1"+"', '"+
                "0"+"', '"+"0"+"', '"+
                "Aircraft Damage"+"', '"+ "Aircraft Tank"+"', '"+"Aircraft Speed"+"', '"+
                "Shooter Damage"+"', '"+"Shooter Tank"+"', '"+"Shooter Speed"+"', '"+
                "Melee Damage"+"', '"+"Melee Tank"+"', '"+"Melee Speed"+"');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" ("+COL_PSWD+", "+
                COL_NAME+", "+COL_WIN+", "+COL_TOTGAMES+", "+
                COL_UNIT1+", "+COL_UNIT2+", "+COL_UNIT3+", "+
                COL_UNIT4+", "+COL_UNIT5+", "+COL_UNIT6+", "+
                COL_UNIT7+", "+COL_UNIT8+", "+COL_UNIT9+") VALUES ('"+
                "player2"+"', '"+"player2"+"', '"+
                "0"+"', '"+"0"+"', '"+
                "Aircraft Damage"+"', '"+ "Aircraft Tank"+"', '"+"Aircraft Speed"+"', '"+
                "Shooter Damage"+"', '"+"Shooter Tank"+"', '"+"Shooter Speed"+"', '"+
                "Melee Damage"+"', '"+"Melee Tank"+"', '"+"Melee Speed"+"');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_USERS);
        onCreate(db);
    }
}
