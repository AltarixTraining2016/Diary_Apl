package com.example.user.diary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by User on 01.07.2016.
 */
public class testActivity extends AppCompatActivity {

    EditText idEditText;
    EditText nameEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        idEditText = (EditText) findViewById(R.id.etID);
        nameEdiText = (EditText) findViewById(R.id.etName);

        ContentValues cv = new ContentValues();
        cv.put("_id", "2");
        cv.put("name", "rrrr");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);

        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                Log.v("Sah", "select " + id);
                Cursor c = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id, name FROM table_list_name_case WHERE _id = ?", new String[]{id});
                if (c.moveToFirst()) {
                    String _id = c.getString(0);
                    String name = c.getString(1);
                    nameEdiText.setText(_id + " " + name);
                } else {
                    Log.w("Sah", "empty!");
                }
            }
        });

        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String name = nameEdiText.getText().toString();
                Log.v("Sah", "inser " + id + " " + name);
                ContentValues cv = new ContentValues();
                cv.put("_id", id);
                cv.put("name", name);
                DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
            }
        });
        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String name = nameEdiText.getText().toString();
                Log.v("Sah", "update " + id + " " + name);
                ContentValues cv = new ContentValues();
                cv.put("name", name);
                DataBaseHelper.getInstance().getWritableDatabase().update("table_list_name_case", cv, "_id = ?", new String[]{id});
            }
        });

        findViewById(R.id.b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                Log.v("Sah", "delete " + id);
                DataBaseHelper.getInstance().getWritableDatabase().delete("table_list_name_case", "_id = ?", new String[]{id});
            }
        });

        findViewById(R.id.b5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //File f = getDatabasePath(DataBaseHelper.getInstance().getDatabaseName());
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS), DataBaseHelper.getInstance().getDatabaseName());

                try{
                    ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(file));
                    os.writeObject(file);
                    os.close();
                    Log.v("Sah", "db save ");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.b6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "-1";
                TextView tv = (TextView)findViewById(R.id.tv_db);
                Cursor c = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id, name FROM table_list_name_case", null);

                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        String _id;
                        String name;
                        _id = c.getString(0);
                        name = c.getString(1);
                        tv.setText(tv.getText()+_id + " " + name +"\n");
                        c.moveToNext();
                    }
                    //c.moveToFirst();
                    //tv.setText(tv.getText()+getString(c.getCount()));
                } else {
                    Log.w("Sah", "empty!");
                }
                //c.moveToLast();
                //int i = c.getPosition();
                //tv.setText(tv.getText()+" "+getString(i));

            }
        });

    }

}
