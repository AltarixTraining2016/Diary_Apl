package com.example.user.diary;

import android.app.Application;
import android.app.DatePickerDialog;

/**
 * Created by User on 02.07.2016.
 */
public class DataBaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseHelper.init(getApplicationContext());
    }

}
