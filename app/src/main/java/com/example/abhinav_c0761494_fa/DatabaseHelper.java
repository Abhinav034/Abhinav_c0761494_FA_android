package com.example.abhinav_c0761494_fa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "persons.db";
    private static final String TABLE_NAME = "person";
    private static final String COL2 = "FIRSTNAME";
    private static final String COL3 = "LASTNAME";
    private static final String COL4 = "PHONENUMBER";
    private static final String COL5 = "ADDRESS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = ("CREATE TABLE "+ TABLE_NAME + "(FIRSTNAME VARCHAR(100) , LASTNAME VARCHAR(100) , PHONENUMBER VARCHAR(100), ADDRESS VARCHAR(100))");
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = ("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);

    }

    public boolean insert(String firstname , String lastname , String phone , String address){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2 , firstname);
        contentValues.put(COL3 , lastname);
        contentValues.put(COL4 , phone);
        contentValues.put(COL5 , address);

       long result =  db.insert(TABLE_NAME , null , contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM "+ TABLE_NAME , null);
        return res;


    }
    public Integer deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME , COL2 +"=?" ,new String[]{name});

    }

    public boolean updateData (String newFname , String newLname , String newNumber , String newAddress , String name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2 , newFname);
        contentValues.put(COL3 , newLname);
        contentValues.put(COL4 , newNumber);
        contentValues.put(COL5 , newAddress);

        db.update(TABLE_NAME , contentValues , COL2 +"=?" , new String[]{name});
        return true;

    }




}
