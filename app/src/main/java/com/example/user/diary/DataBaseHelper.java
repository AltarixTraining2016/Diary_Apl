package com.example.user.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 01.07.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "cases.sqlite";
    private static final int VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context,NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" + "_id integer primary key autoincrement, " + "names text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
