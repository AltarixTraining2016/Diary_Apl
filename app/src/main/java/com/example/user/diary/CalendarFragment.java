package com.example.user.diary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by User on 25.06.2016.
 */
public class CalendarFragment extends Fragment implements Titleable{
    public static CalendarFragment create() {
        return new CalendarFragment();
    }

    //@BindView(R.id.calendarView)
    //CalendarView cv;

    TextView dateDisplay;
    DatePicker dp;
    FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_layout, container, false);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        dateDisplay = (TextView)v.findViewById(R.id.date_display);
        dateDisplay.setText("Сегодня: "+dateFormat.format(new Date()));

        dp = (DatePicker)v.findViewById(R.id.datePicker);

        Button btds = (Button)v.findViewById(R.id.button_date_select);
        btds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                CaseListFragment clf = new CaseListFragment();
                Bundle bundle = new Bundle();
                String date = "";
                if(dp.getDayOfMonth()<10) date +="0"+dp.getDayOfMonth();
                else  date+=dp.getDayOfMonth();
                date +=".";
                if((dp.getMonth()+1)<10) date +="0"+(dp.getMonth()+1);
                else  date+=dp.getMonth();
                date +="."+dp.getYear();
                bundle.putString("DATA_CASE_LIST", date);
                clf.setArguments(bundle);
                ft.replace(R.id.container_content, clf);
                ft.commit();
                if (clf instanceof Titleable) {
                    String title = ((Titleable) clf).getTitle(getActivity());
                    getActivity().setTitle(title);
                }
                //
            }
        });



        return v;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_calendar);
    }
}
