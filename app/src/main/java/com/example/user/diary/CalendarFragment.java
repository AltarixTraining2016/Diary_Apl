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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_layout, container, false);


        //cv = (CalendarView) getActivity().findViewById(R.id.calendarView);
       /* cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                //String selectedDate = new StringBuilder().append(mMonth + 1)
                 //       .append("-").append(mDay).append("-").append(mYear)
                 //       .append(" ").toString();
                //Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
                //Toast.makeText(getContext(), selectedDate, Toast.LENGTH_LONG).show();

            }
        });*/

        return v;
    }
}
