package com.example.user.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 01.07.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper ins = null;

    public static void init(Context context){
        ins = new DataBaseHelper(context);
    }

    public static DataBaseHelper getInstance(){
        return ins;
    }

    private DataBaseHelper(Context context) {
        super(context, "diary.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
        sqLiteDatabase.execSQL("CREATE TABLE table_list_name_case (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE table_list_case (_id INTEGER PRIMARY KEY AUTOINCREMENT, name_id INTEGER NOT NULL,"
                +" description TEXT, date TEXT NOT NULL, time_start TEXT, time_end TEXT, status INTEGER NOT NULL, color INTEGER NOT NULL);");
        //sqLiteDatabase.execSQL("CREATE TABLE table_list_case (_id INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY (name_id) REFERENCES table_list_name_case (_id) ON DELETE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
