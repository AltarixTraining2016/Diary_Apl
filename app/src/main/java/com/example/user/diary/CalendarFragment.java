package com.example.user.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

/**
 * Created by User on 25.06.2016.
 */
public class CalendarFragment extends Fragment {
    public static CalendarFragment create() {
        return new CalendarFragment();
    }

    @BindView(R.id.calendarView)
    CalendarView cv;
    TextView dateDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_layout, container, false);


        cv = (CalendarView) v.findViewById(R.id.calendarView);
        dateDisplay = (TextView)v.findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText("Date: " + i2 + " / " + (i1+1) + " / " + i);

                //Toast.makeText(getActivity().getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
