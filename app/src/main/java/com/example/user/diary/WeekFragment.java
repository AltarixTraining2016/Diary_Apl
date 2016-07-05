package com.example.user.diary;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 25.06.2016.
 */
public class WeekFragment extends Fragment implements Titleable{
    public static WeekFragment create() {
        return new WeekFragment();
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;



    List<String> ls = new ArrayList<>();
    RecyclerView rv;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.week_layout, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

        mViewPager = (ViewPager) v.findViewById(R.id.container_cont);
        mViewPager.setAdapter(mSectionsPagerAdapter);




        return v;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_week);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        RecyclerView rv;
        List<String> ls = new ArrayList<>();
        List<String> wd= new ArrayList<String>();

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.week_layout_cont, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);

            wd.add("Понедельник");
            wd.add("Вторник");
            wd.add("Среда");
            wd.add("Четверг");
            wd.add("Пятница");
            wd.add("Суббота");
            wd.add("Воскресенье");

            textView.setText(wd.get(getArguments().getInt(ARG_SECTION_NUMBER)));
            textView.setTextSize(24);
            setRecycler(rootView,R.id.rec_week);


            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            ls.clear();
            TextView tvData = (TextView)rootView.findViewById(R.id.week_date);
            tvData.setTextSize(24);
            String date = getDate(getArguments().getInt(ARG_SECTION_NUMBER));
            tvData.setText(date); //dateFormat.format(new Date()));

            Cursor cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name_id FROM table_list_case WHERE date = ?", new String[]{date});
            Cursor cursor_dop = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name FROM table_list_name_case", null);
            if (cursor.moveToFirst()) {
                cursor_dop.moveToFirst();
                while (!cursor.isAfterLast()) {
                    cursor_dop.moveToPosition(cursor.getInt(0)-1);
                    ls.add(cursor_dop.getString(0));
                    cursor.moveToNext();
                }
            } else {
                Log.w("Sah", "empty!");
            }
            cursor.close();
            cursor_dop.close();

            return rootView;
        }

        public String getDate(int j){
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEE");
            //SimpleDateFormat dateFormat = new SimpleDateFormat("u");
            Date d = new Date();
            int i = 0;// = Integer.parseInt(dateFormat.format(new Date()));
            int day;
            if(dateFormat.format(d)=="пн") i = 1;
            else if (dateFormat.format(d)=="вт") i = 2;
            else if (dateFormat.format(d)=="ср") i = 3;
            else if (dateFormat.format(d)=="чт") i = 4;
            else if (dateFormat.format(d)=="пт") i = 5;
            else if (dateFormat.format(d)=="сб") i = 6;
            else if (dateFormat.format(d)=="вс") i = 7;

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);


            if(j<i){
                day = i-j;
                calendar.add(Calendar.DATE, -1*day);
            }
            else if(j>i){
                day = j-i;
                calendar.add(Calendar.DATE, day);
            }
            dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            d = calendar.getTime();
            return dateFormat.format(d);
        }

        public  void setRecycler(View v,int id){
            rv = (RecyclerView)v.findViewById(id);


            rv.setAdapter(new Adapter());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        class ViewHolder extends RecyclerView.ViewHolder{

            TextView tv;
            //LinearLayout ll;

            public ViewHolder(View itemView) {
                super(itemView);
                //ll = (LinearLayout) itemView.findViewById(R.id.ll);
                tv = (TextView) itemView.findViewById(R.id.textViewRecycler);
            }
        }

        class Adapter extends RecyclerView.Adapter<ViewHolder>{

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                //return new ViewHolder(parent);
                //return new ViewHolder(View.inflate(MainActivity.this, R.layout.listitem_card, null));
                return new ViewHolder(View.inflate(getContext(), R.layout.listitem_card, null));
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.tv.setText(ls.get(position));
                //if(position%2==0) holder.ll.setBackgroundColor(getResources().getColor(R.color.tvBackground));
            }

            @Override
            public int getItemCount() {
                return ls.size();
            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 0";
                case 1:
                    return "SECTION 1";
                case 2:
                    return "SECTION 2";
                case 3:
                    return "SECTION 3";
                case 4:
                    return "SECTION 4";
                case 5:
                    return "SECTION 5";
                case 6:
                    return "SECTION 6";
            }
            return null;
        }
    }


}
