package com.example.myapplicationweatherapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    String TABLE_NAME = "Citydbo";

    public SQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "cityTitle TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertMovies( String cityTitle) {

        String INSERT_STUDENT_QUERY = "INSERT INTO " + TABLE_NAME + "(cityTitle) VALUES("
                + "'" + cityTitle.toLowerCase() + "'"
                + ")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_STUDENT_QUERY);
        db.close();
    }
    public ArrayList<String> getAllStudentsName() {
        ArrayList<String> MoviesArry =new ArrayList<>();
        String GET_ALL_STUDENTS_NAME_QUERY = "SELECT cityTitle FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(GET_ALL_STUDENTS_NAME_QUERY, null);
        while (data.moveToNext()){
            MoviesArry.add(data.getString(0).substring(0,1).toUpperCase() + data.getString(0).substring(1));
        }
//        Log.d("Zizisql", String.valueOf(MoviesArry.size()));
        db.close();
        return MoviesArry;
    }
    public int CheckItemifSaved(String CountryName) {
        ArrayList<String> MoviesArry =new ArrayList<>();
        //String result = "";
        String GET_ALL_STUDENTS_NAME_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE cityTitle='"+CountryName.toLowerCase()+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(GET_ALL_STUDENTS_NAME_QUERY, null);
        while (data.moveToNext()){
            MoviesArry.add(data.getString(0));
        }
        db.close();
        return MoviesArry.size();
    }
}

