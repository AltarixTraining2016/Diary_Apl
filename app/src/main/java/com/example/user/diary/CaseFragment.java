package com.example.user.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 08.06.2016.
 */
public class CaseFragment extends Fragment implements Titleable{

    //BUTTONS
    @BindView(R.id.button_save_case)
    Button bt_save;
    @OnClick(R.id.button_save_case)
    public void onClickSave(View view) {
        String idd = String.valueOf(id);
        String name_id = String.valueOf(sp_name.getSelectedItemPosition()+1);
        String description = String.valueOf(et.getText());
        String d = "";
        if(dp.getDayOfMonth()<10) d +="0"+dp.getDayOfMonth();
        else  d+=dp.getDayOfMonth();
        d +=".";
        if((dp.getMonth()+1)<10) d +="0"+(dp.getMonth()+1);
        else  d+=dp.getMonth();
        d +="."+dp.getYear();

        String time_start = "";
        String time_end = "";
        //NumberPicker np = (NumberPicker)getActivity().findViewById(R.id.n_start_hour);
        if(String.valueOf(np_start_hour.getValue()).length()==1)
            time_start = "0"+ String.valueOf(np_start_hour.getValue())+":";
        else time_start = String.valueOf(np_start_hour.getValue())+":";
        //np = (NumberPicker)getActivity().findViewById(R.id.n_start_minut);
        if(String.valueOf(np_start_minut.getValue()).length()==1)
            time_start += "0"+ String.valueOf(np_start_minut.getValue());
        else time_start += String.valueOf(np_start_minut.getValue());

        //np = (NumberPicker)getActivity().findViewById(R.id.n_end_hour);
        if(String.valueOf(np_end_hour.getValue()).length()==1)
            time_end = "0" + String.valueOf(np_end_hour.getValue())+":";
        else time_end = String.valueOf(np_end_hour.getValue())+":";
        //np = (NumberPicker)getActivity().findViewById(R.id.n_end_minut);
        if(String.valueOf(np_end_minut.getValue()).length()==1)
            time_end += "0" + String.valueOf(np_end_minut.getValue());
        else time_end += String.valueOf(np_end_minut.getValue());


        String status = "";
        if(sw.isChecked())status = "1";
        else status = "0";
        String color = String.valueOf(sp_color.getSelectedItemPosition());

        cv = new ContentValues();
        cv.clear();

        if(newCase==1){
            newCase = 0;
            int id = 0;
            idd="";
            Cursor cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id FROM table_list_case", null);

            if (cursor.moveToFirst()) {
                cursor.moveToLast();
                id = cursor.getInt(0);
                id++;
                idd = String.valueOf(id);
            } else {
                id = 1;
                idd = String.valueOf(id);
                Log.w("Sah", "empty!");
            }
            cursor.close();
            //ContentValues cv = new ContentValues();
            cv.put("_id", idd);
            cv.put("name_id", name_id);
            cv.put("description", description);
            cv.put("date", d);
            cv.put("time_start", time_start);
            cv.put("time_end", time_end);
            cv.put("status", status);
            cv.put("color", color);

            DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_case", null, cv);
        }
        else {
            cv.put("name_id", name_id);
            cv.put("description", description);
            cv.put("date", d);
            cv.put("time_start", time_start);
            cv.put("time_end", time_end);
            cv.put("status", status);
            cv.put("color", color);
            DataBaseHelper.getInstance().getWritableDatabase().update("table_list_case", cv, "_id = ?", new String[]{idd});
        }

        CaseListFragment cf = new CaseListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DATA_CASE_LIST",d);
        cf.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, cf).commit();
        if (cf instanceof Titleable) {
            String title = ((Titleable) cf).getTitle(getActivity());
            getActivity().setTitle(title);
        }

    }


    @BindView(R.id.button_cancel_case)
    Button bt_cancel;
    @OnClick(R.id.button_cancel_case)
    public void onClickCancel(View view) {
        //String description = String.valueOf(et.getText());
        //String idd = String.valueOf(id);

        CaseListFragment cf = new CaseListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DATA_CASE_LIST",date);
        cf.setArguments(bundle);

        //getFragmentManager().popBackStack();
        //getActivity().getSupportFragmentManager().popBackStack();
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, cf).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, cf).commit();
        if (cf instanceof Titleable) {
            String title = ((Titleable) cf).getTitle(getActivity());
            getActivity().setTitle(title);
        }
    }


    @BindView(R.id.button_delete_case)
    Button bt_delete;
    @OnClick(R.id.button_delete_case)
    public void onClickDelete(View view) {
        if(newCase==0){
            String idd = String.valueOf(id);
            DataBaseHelper.getInstance().getWritableDatabase().delete("table_list_case", "_id = ?", new String[]{idd});
        }
        CaseListFragment cf = new CaseListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DATA_CASE_LIST",date);
        cf.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, cf).commit();
        if (cf instanceof Titleable) {
            String title = ((Titleable) cf).getTitle(getActivity());
            getActivity().setTitle(title);
        }
    }

    //SPUNNERS
    @BindView(R.id.spinner_name)
    Spinner sp_name;
    @BindView(R.id.spinner_color)
    Spinner sp_color;

    //NUMBER PICKERS
    @BindView(R.id.n_start_hour)
    NumberPicker np_start_hour;
    @BindView(R.id.n_start_minut)
    NumberPicker np_start_minut;
    @BindView(R.id.n_end_hour)
    NumberPicker np_end_hour;
    @BindView(R.id.n_end_minut)
    NumberPicker np_end_minut;

    //OTHER
    @BindView(R.id.date_case)
    DatePicker dp;

    @BindView(R.id.editText_description)
    EditText et;

    @BindView(R.id.switch_case_end)
    Switch sw;

    public static final String DATE_CASE = "date_case";
    public static final String POSITION = "position";
    public static final String NEW_CASE = "new_case";

    List<String> caseNameList = new ArrayList<String>();
    String date;
    ContentValues cv;
    int id;
    int newCase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_layout, container, false);

        ButterKnife.bind(this,v);
        ///////////////////////////////////////////////////////////////////////
        Cursor cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name FROM table_list_name_case", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                caseNameList.add(cursor.getString(0));
                cursor.moveToNext();
            }
        } else {
            Log.w("Sah", "empty!");
        }
        cursor.close();
        ///////////////////////////////////////////////////////////////////////

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, caseNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_name.setAdapter(adapter);

        MyCustomAdapter ad = new MyCustomAdapter(getContext(),
                R.layout.spinner_dropdown_item, new String[]{"0","1","2","3","4","5"});
        sp_color.setAdapter(ad);
        ////////////////////////////////////////////////////////////

        np_start_hour.setMaxValue(23);
        np_start_hour.setMinValue(0);

        np_start_minut.setMaxValue(59);
        np_start_minut.setMinValue(0);

        np_end_hour.setMaxValue(23);
        np_end_hour.setMinValue(0);

        np_end_minut.setMaxValue(59);
        np_end_minut.setMinValue(0);
        ///////////////////////////////////////////////////////////////

        final int position = this.getArguments().getInt(POSITION);
        date = this.getArguments().getString(DATE_CASE);
        newCase = this.getArguments().getInt(NEW_CASE);
        assert date != null;
        String month = date.substring(3,5);
        String day = date.substring(0,2);
        String year = date.substring(6);

        if(newCase==1){
            sp_name.setSelection(0);
            et.setText("");
            dp.init(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), null);

            np_start_hour.setValue(0);
            np_start_minut.setValue(0);
            np_end_hour.setValue(0);
            np_end_minut.setValue(0);

            sw.setChecked(false);

            sp_color.setSelection(0);
        }
        else {
            cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT * FROM table_list_case WHERE date = ?", new String[]{date});
            if (cursor.moveToFirst()) {
                cursor.moveToPosition(position);

                id = cursor.getInt(0);
                sp_name.setSelection(cursor.getInt(1) - 1);
                et.setText(cursor.getString(2));
                dp.init(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), null);
                //
                np_start_hour.setValue(Integer.valueOf(cursor.getString(4).substring(0,2)));
                np_start_minut.setValue(Integer.valueOf(cursor.getString(4).substring(3,5)));
                np_end_hour.setValue(Integer.valueOf(cursor.getString(5).substring(0,2)));
                np_end_minut.setValue(Integer.valueOf(cursor.getString(5).substring(3,5)));
                //
                if (cursor.getInt(6) == 0) sw.setChecked(false);
                else sw.setChecked(true);

                sp_color.setSelection(cursor.getInt(7));

            } else {
                Log.w("Sah", "empty!");
            }
            cursor.close();
        }
        return v;
    }

    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,ViewGroup parent) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
            switch (position) {
                case 0:
                    v.setBackgroundResource(R.drawable.style_color_red);
                    break;
                case 1:
                    v.setBackgroundResource(R.drawable.style_color_green);
                    break;
                case 2:
                    v.setBackgroundResource(R.drawable.style_color_blue);
                    break;
                case 3:
                    v.setBackgroundResource(R.drawable.style_color_orange);
                    break;
                case 4:
                    v.setBackgroundResource(R.drawable.style_color_violet);
                    break;
                case 5:
                    v.setBackgroundResource(R.drawable.style_color_yellow);
                    break;
            }
            return v;
        }
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_case);
    }


}
