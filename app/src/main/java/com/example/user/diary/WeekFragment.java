package com.example.user.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.06.2016.
 */
public class WeekFragment extends Fragment {
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



        //setRecycler(v,R.id.rec_mond);
        //setRecycler(v,R.id.rec_tues);
        //setRecycler(v,R.id.rec_wedn);
        //setRecycler(v,R.id.rec_thur);
        //setRecycler(v,R.id.rec_frid);
        //setRecycler(v,R.id.rec_satur);
        //setRecycler(v,R.id.rec_san);

        return v;
    }
/*
    public  void setRecycler(View v,int id){
        rv = (RecyclerView)v.findViewById(id);
        for(int i = 0; i<20;i++)
            if(i%2!=0)
                ls.add("case "+i);
            else ls.add("case  /nffffffffff      "+i);
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
    }*/

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        RecyclerView rv;
        List<String> ls = new ArrayList<>();

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
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            setRecycler(rootView,R.id.rec_week);
            //CheckBox cb = (CheckBox)rootView.findViewById(R.id.checkBox);
            //if(getArguments().getInt(ARG_SECTION_NUMBER)%2==0)
            //    cb.setChecked(true);
            //else cb.setChecked(false);
            return rootView;
        }

        public  void setRecycler(View v,int id){
            rv = (RecyclerView)v.findViewById(id);
            for(int i = 0; i<20;i++)
                if(i%2!=0)
                    ls.add("case "+i);
                else ls.add("case  /nffffffffff      "+i);
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
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }


}
