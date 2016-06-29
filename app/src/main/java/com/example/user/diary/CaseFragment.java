package com.example.user.diary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 08.06.2016.
 */
public class CaseFragment extends Fragment {

    List<String> testList = new ArrayList<String>();

    Button bts;
    Button btc;
    Spinner sp;

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


        sp = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, testList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);


        return v;
    }


}
