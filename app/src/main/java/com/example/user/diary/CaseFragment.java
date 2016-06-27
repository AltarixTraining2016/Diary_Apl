package com.example.user.diary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.Spinner;

/**
 * Created by User on 08.06.2016.
 */
public class CaseFragment extends Fragment implements TextWatcher{

    String[] stri = new String[] { "Зима", "Весна", "Лето", "Осень" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_layout, container, false);


        AutoCompleteTextView acv = (AutoCompleteTextView)v.findViewById(R.id.autoCompleteTextView);
        //acv.addTextChangedListener((TextWatcher) getContext());
        //acv.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line, stri));

        return v;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
