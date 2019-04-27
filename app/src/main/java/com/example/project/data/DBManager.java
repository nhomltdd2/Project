package com.example.project.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.DataModel;
import com.example.project.model.NamePlayer;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "name_player_manager";
    private static final String TABLE_NAME = "name_player";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SCORE = "score";
    private static int VERSION = 1;
    private Context context;
    // thy: sửa lại chút xíu câu Query, thêm autoincrement, và trường score - điểm
    private String SQLQuery ="CREATE TABLE "+TABLE_NAME+" ("+
            ID +" integer primary key AUTOINCREMENT, "+
            NAME + " TEXT, "+
            SCORE+" TEXT)";
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        // thy: chỗ này là gọi hàm insert dữ liệu mẫu, sau này đóng lại

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

    //thy: đây là hàm lấy ds players
    public void get_all_players(ArrayList<DataModel> members){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from name_player",null);
        if(cursor.moveToFirst()){
            do {
                DataModel data = new DataModel();
                data.setId_(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                data.setName(cursor.getString(cursor.getColumnIndex("name")));
                data.setRank(cursor.getString(cursor.getColumnIndex("score")));
                members.add(data);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    // thy: hàm này để insert dữ liệu mẫu test màn hình rank
    // sau này app chạy ok rồi thì đóng lại
    public void insert_du_lieu_mau(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(NAME, "Ten nguoi choi 1");
        value.put(SCORE, "100");
        db.insert(TABLE_NAME, null, value);

        value.put(NAME, "Ten nguoi choi 2");
        value.put(SCORE, "200");
        db.insert(TABLE_NAME, null, value);

        value.put(NAME, "Ten nguoi choi 3");
        value.put(SCORE, "300");
        db.insert(TABLE_NAME, null, value);

        value.put(NAME, "Ten nguoi choi 4");
        value.put(SCORE, "400");
        db.insert(TABLE_NAME, null, value);
        db.close();
    }

    //thy: hàm reset table - xóa hết record
    public void clear_all(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from "+TABLE_NAME+" where 1";
        db.execSQL(sql);


        db.close();
    }
}
