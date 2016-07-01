package com.example.user.diary;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by User on 01.07.2016.
 */
public class testActivity extends AppCompatActivity {

    private DataBaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);



    }


    public void onClick(View v) {
        mDatabaseHelper = new DataBaseHelper(this);
        SQLiteDatabase sdb;
        sdb = mDatabaseHelper.getReadableDatabase();
    }

}
