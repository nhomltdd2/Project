package com.example.project.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.model.NamePlayer;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "name_player_manager";
    private static final String TABLE_NAME = "name_player";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static int VERSION = 1;
    private Context context;
    private String SQLQuery ="CREATE TABLE "+TABLE_NAME+" ("+
            ID +" integer primary key, "+
            NAME + " TEXT)";
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addName(NamePlayer namePlayer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, namePlayer.getmName());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
