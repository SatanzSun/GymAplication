package com.example.mysqlproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("create Table Userdetails(username TEXT primary key, password Text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
    DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);

        Long result=DB.insert("Userdetails", null, contentValues);
if (result==-1){
    return false;
}else{
    return true;
}
    }

    public Boolean updateuserdata(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username); //same?
        contentValues.put("password", password);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});
        if(cursor.getCount()> 0){
        long result=DB.update("Userdetails", contentValues, "username=?", new String[] {username});
        if (result==-1){
            return false;
        }else {
        }
            return true;
        }else{
return false;
            }

    }

    }

