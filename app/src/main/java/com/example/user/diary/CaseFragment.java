package com.example.user.diary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 08.06.2016.
 */
public class CaseFragment extends Fragment implements Titleable{

    List<String> testList = new ArrayList<String>();

    Button bts;
    Button btc;
    Spinner sp;
    Spinner sp_color;

    private final String DATE_CASE = "date_case";
    private final String POSITION = "position";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_layout, container, false);

        bts = (Button)v.findViewById(R.id.button_save_case);
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bts.setBackgroundResource(R.drawable.style_button_cancel);
                //
            }
        });

        btc = (Button)v.findViewById(R.id.button_cancel_case);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btc.setBackgroundResource(R.drawable.style_button_save);
                //
            }
        });



        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("werwer");
        testList.add("e3e");

        EditText et = (EditText)v.findViewById(R.id.editText_description);
        et.setText(this.getArguments().getString(DATE_CASE)+" "+this.getArguments().getInt(POSITION));



        sp = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, testList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                //TextView ttt = (TextView)getActivity().findViewById(R.id.textView2);
                //ttt.setText(ttt.getText()+" "+selectedItemPosition);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        MyCustomAdapter ad = new MyCustomAdapter(getContext(),
                R.layout.spinner_dropdown_item, new String[]{"0","1","2","3","4","5"});

        sp_color = (Spinner)v.findViewById(R.id.spinner_color);
        sp_color.setAdapter(ad);



        return v;
    }

    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {
            // TODO Auto-generated method stub
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
            //TextView label = (TextView) row.findViewById(R.id.weekofday);
            //label.setText(testList[position]);

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
