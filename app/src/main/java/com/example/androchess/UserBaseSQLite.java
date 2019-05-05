package com.example.androchess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBaseSQLite extends SQLiteOpenHelper {
    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COl_WIN ="WIN";
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
    private static final String COL_UNIT10 = "UNIT10";
    private static final String COL_PSWD = "PSWD";
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS "+TABLE_USERS+" ("+COL_NAME+" " +
            "TEXT PRIMARY KEY NOT NULL, "+
            COl_WIN+" INTEGER NOT NULL DEFAULT 0, "+COL_TOTGAMES+" INTEGER NOT NULL DEFAULT 0, "+
            COL_UNIT1+" TEXT NOT NULL, "+ COL_UNIT2+" TEXT NOT NULL, "+
            COL_UNIT3+" TEXT NOT NULL, "+ COL_UNIT4+" TEXT NOT NULL, "+
            COL_UNIT5+" TEXT NOT NULL, "+ COL_UNIT6+" TEXT NOT NULL, "+
            COL_UNIT7+" TEXT NOT NULL, "+ COL_UNIT8+" TEXT NOT NULL, "+
            COL_UNIT9+" TEXT NOT NULL, "+ COL_UNIT10+" TEXT NOT NULL, "+
            COL_PSWD+" TEXT NOT NULL);";
    public UserBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_USERS);
        onCreate(db);
    }
}
