package com.example.user.diary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;

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

        setTables();



        /////////////////////////////////////////////select
        /*
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
        */


        Button but_ins = (Button)findViewById(R.id.button_insert);
        assert but_ins != null;
        but_ins.setOnClickListener(new View.OnClickListener() {
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

        Button but_upd = (Button)findViewById(R.id.button_update);
        assert but_upd != null;
        but_upd.setOnClickListener(new View.OnClickListener() {
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

        Button but_del = (Button)findViewById(R.id.button_delete);
        assert but_del != null;
        but_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                Log.v("Sah", "delete " + id);
                DataBaseHelper.getInstance().getWritableDatabase().delete("table_list_name_case", "_id = ?", new String[]{id});
            }
        });

        //save db in memory
        /*
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
        */

        Button but_disp = (Button)findViewById(R.id.button_display);
        assert but_disp != null;
        but_disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _id;
                String name;
                TextView tv_name = (TextView)findViewById(R.id.tv_name_db);
                tv_name.setText("");
                Cursor c1 = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id, name FROM table_list_name_case", null);
                if (c1.moveToFirst()) {
                    while (!c1.isAfterLast()) {

                        _id = c1.getString(0);
                        name = c1.getString(1);
                        tv_name.setText(tv_name.getText()+_id + " " + name +"\n");
                        c1.moveToNext();
                    }
                    //c.moveToFirst();
                    //tv.setText(tv.getText()+getString(c.getCount()));
                } else {
                    Log.w("Sah", "empty!");
                }


                TextView tv_case = (TextView)findViewById(R.id.tv_case_db);
                tv_case.setText("");
                int id;
                //c1.moveToFirst();
                Cursor c2 = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id, name_id FROM table_list_case", null);
                if (c2.moveToFirst()) {
                    while (!c2.isAfterLast()) {
                        _id = c2.getString(0);
                        name = c2.getString(1);

                        id = c2.getInt(1);// Integer.parseInt(name);
                        c1.moveToPosition(id-1);

                        //name = c1.getString(1);
                        //tv_case.setText(tv_case.getText()+name+" " + c1.getString(1) +"\n");
                        tv_case.setText(tv_case.getText()+_id + " "+ c1.getString(1) +"\n");
                        c2.moveToNext();
                    }
                    //c.moveToFirst();
                    //tv.setText(tv.getText()+getString(c.getCount()));
                } else {
                    Log.w("Sah", "empty!");
                }


            }
        });

    }

    public void setTables(){
        ContentValues cv = new ContentValues();
        cv.put("_id", "1");  cv.put("name", "универ");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "2");  cv.put("name", "курсы");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "3");  cv.put("name", "ино");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "4");  cv.put("name", "магазин");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "5");  cv.put("name", "гулять");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);

        //////////////////////////////////////////////////
        cv.clear();
        cv.put("_id", "1");  cv.put("name_id", "1");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "2");  cv.put("name_id", "2");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "3");  cv.put("name_id", "3");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "4");  cv.put("name_id", "4");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "5");  cv.put("name_id", "5");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "6");  cv.put("name_id", "3");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "7");  cv.put("name_id", "5");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);

    }

}
