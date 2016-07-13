package com.example.user.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.06.2016.
 */
public class CaseListFragment extends Fragment implements Titleable{

    public CaseListFragment(){}
    public CaseListFragment(String data){}
    String date;
    Cursor cursor;

    //public static CaseListFragment create() {return new CaseListFragment();}
    public static CaseListFragment create(){//String data) {
        CaseListFragment cf = new CaseListFragment();
        //Bundle args = new Bundle();
        //args.putString(DATA_CASE_LIST, data);
        //cf.setArguments(args);
        return cf;
    }

    private final String DATA_CASE_LIST = "data_case_list";
    List<String> ls = new ArrayList<>();
    List<Integer> colorId = new ArrayList<>();
    List<Integer> status = new ArrayList<>();

    /*public CaseListFragment(String data){
        CaseListFragment cf = new CaseListFragment();
        Bundle args = new Bundle();
        args.putString(DATA_CASE_LIST, data);
        cf.setArguments(args);
        //return cf;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_list_layout, container, false);
        RecyclerView rv = (RecyclerView)v.findViewById(R.id.recycler);

        //for(int i = 0; i<20;i++)
        //    if(i%2!=0)
        //        ls.add("case "+i);
        //    else ls.add("case  /nffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff      "+i);

        date = this.getArguments().getString("DATA_CASE_LIST");
        TextView tvData = (TextView)v.findViewById(R.id.text_data_case_list);
        tvData.setText(date);


        cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name_id,color,status FROM table_list_case WHERE date = ?", new String[]{date});
        Cursor cursor_dop = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name FROM table_list_name_case", null);
        if (cursor.moveToFirst()) {
            cursor_dop.moveToFirst();
            while (!cursor.isAfterLast()) {
                cursor_dop.moveToPosition(cursor.getInt(0)-1);
                ls.add(cursor_dop.getString(0));
                colorId.add(cursor.getInt(1));
                status.add(cursor.getInt(2));
                cursor.moveToNext();
            }
        } else {
            Log.w("Sah", "empty!");
        }
        cursor.close();
        cursor_dop.close();




        rv.setAdapter(new Adapter());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_case_list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = ls.size();
                Bundle bundle = new Bundle();
                bundle.putString(CaseFragment.DATE_CASE,date);;
                bundle.putInt(CaseFragment.POSITION,position);
                bundle.putInt(CaseFragment.NEW_CASE,1);
                CaseFragment cf = new CaseFragment();
                cf.setArguments(bundle);

                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, cf).commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, cf).addToBackStack("tag").commit();
                if (cf instanceof Titleable) {
                    String title = ((Titleable) cf).getTitle(getActivity());
                    getActivity().setTitle(title);
                }

           }
        });

        return v;
    }



    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_list_Case);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv;
        LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
            tv = (TextView) itemView.findViewById(R.id.textViewRecycler);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Item Listener", ""+this.getLayoutPosition());
            int position = this.getLayoutPosition();
            Bundle bundle = new Bundle();
            bundle.putString(CaseFragment.DATE_CASE,date);//"DATE_CASE",date);
            bundle.putInt(CaseFragment.POSITION,position);//"POSITION",position);
            bundle.putInt(CaseFragment.NEW_CASE,0);
            CaseFragment fragment = new CaseFragment();
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, fragment).commit();
            if (fragment instanceof Titleable) {
                String title = ((Titleable) fragment).getTitle(getActivity());
                getActivity().setTitle(title);
            }
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

            setColor(colorId.get(position),status.get(position),holder.ll);
            //if(position%2==0) holder.ll.setBackgroundResource(R.drawable.style_button_add_name);//getResources().getColor(R.color.tvBackground));
        }

        public void setColor(int position,int status,LinearLayout ll){
            if(status==1){
                ll.setBackgroundResource(R.drawable.style_card_grey);
            }
            else {
                switch (position) {
                    case 0:
                        ll.setBackgroundResource(R.drawable.style_card_red);
                        break;
                    case 1:
                        ll.setBackgroundResource(R.drawable.style_card_green);
                        break;
                    case 2:
                        ll.setBackgroundResource(R.drawable.style_card_blue);
                        break;
                    case 3:
                        ll.setBackgroundResource(R.drawable.style_card_orange);
                        break;
                    case 4:
                        ll.setBackgroundResource(R.drawable.style_card_violet);
                        break;
                    case 5:
                        ll.setBackgroundResource(R.drawable.style_card_yellow);
                        break;
                }
            }
        }

        @Override
        public int getItemCount() {
            return ls.size();
        }
    }

}
