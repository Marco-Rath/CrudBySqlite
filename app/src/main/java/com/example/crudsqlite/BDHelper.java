package com.example.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper  extends SQLiteOpenHelper {

    public BDHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Userdetails(name TEXT PRIMARY KEY, contact TEXT,dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS Userdetails");
    }
    //INSERT METHODE FOR USERDETAILS
    public Boolean insertuserdata(String name, String contact, String dob){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("name",name);
    contentValues.put("contact",contact);
    contentValues.put("dob",dob);
    long result= db.insert("Userdetails",null,contentValues);
    if(result==-1){
        return false;
    }else{
        return true;
    }
    }
    //UPDATE METHOD FOR USERDETAILS
    public Boolean updateuserdata(String name, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = db.rawQuery("SELECT* FROM Userdetails WHERE name=?", new String[]{name});
        if (cursor.getCount() > 0) {


            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }


    }
    //DELETE METHOD FOR USERDETAILS
    public Boolean deletedata(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM Userdetails WHERE name=?", new String[]{name});
        if (cursor.getCount() > 0) {


            long result = db.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }


    }

    //VIEWDATA METHOD FOR USERDETAILS
    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM Userdetails", null);
        return cursor;
    }


}
