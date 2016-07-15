package com.example.user.diary;

        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;


/**
 * Created by User on 03.06.2016.
 */
public class AboutFragment extends Fragment implements Titleable{

    public static AboutFragment create() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_layout, container, false);

        Button b = (Button)v.findViewById(R.id.button_db);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DataBaseActivity.class);
                startActivity(intent);
            }
        });

        Button bt = (Button)v.findViewById(R.id.button_set_test_table);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTestTables();
            }
        });

        return v;
    }



    public void setTestTables(){
        ContentValues cv = new ContentValues();

        cv.clear();
        cv.put("_id", "1");
        cv.put("name_id", "1");
        cv.put("description", "u");
        cv.put("date", "15.07.2016");
        cv.put("time_start", "11:11");
        cv.put("time_end", "17:56");
        cv.put("status", "0");
        cv.put("color", "0");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "2");
        cv.put("name_id", "2");
        cv.put("description", "urr");
        cv.put("date", "15.07.2016");
        cv.put("time_start", "17:11");
        cv.put("time_end", "19:56");
        cv.put("status", "1");
        cv.put("color", "4");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "3");
        cv.put("name_id", "3");
        cv.put("description", "mr");
        cv.put("date", "15.07.2016");
        cv.put("time_start", "00:00");
        cv.put("time_end", "00:00");
        cv.put("status", "0");
        cv.put("color", "3");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "4");
        cv.put("name_id", "4");
        cv.put("description", "gr");
        cv.put("date", "16.07.2016");
        cv.put("time_start", "17:11");
        cv.put("time_end", "19:56");
        cv.put("status", "0");
        cv.put("color", "2");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "5");
        cv.put("name_id", "5");
        cv.put("description", "fr");
        cv.put("date", "17.07.2016");
        cv.put("time_start", "00:00");
        cv.put("time_end", "00:00");
        cv.put("status", "1");
        cv.put("color", "1");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "6");
        cv.put("name_id", "3");
        cv.put("description", "tr");
        cv.put("date", "17.07.2016");
        cv.put("time_start", "17:11");
        cv.put("time_end", "00:00");
        cv.put("status", "0");
        cv.put("color", "5");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "7");
        cv.put("name_id", "5");
        cv.put("description", "er");
        cv.put("date", "15.07.2016");
        cv.put("time_start", "00:00");
        cv.put("time_end", "00:00");
        cv.put("status", "0");
        cv.put("color", "2");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        cv.put("_id", "8");
        cv.put("name_id", "3");
        cv.put("description", "er");
        cv.put("date", "14.07.2016");
        cv.put("time_start", "00:00");
        cv.put("time_end", "00:00");
        cv.put("status", "0");
        cv.put("color", "5");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_about);
    }
}
