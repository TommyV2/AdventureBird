package com.adventureBird.flappybirdgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Score.db";
    public static final String TABLE_NAME = "score_tabel";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SCORE";

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SCORE INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, score);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(TABLE_NAME,null, null,
                null, null, null,
                " SCORE " +" DESC");
        return res;
    }

    public void clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("UPDATE "+TABLE_NAME+ "SET"+COL_2+" = '0' WHERE id<6 ");
        db.execSQL("UPDATE score_tabel SET SCORE='0'");
    }



}
