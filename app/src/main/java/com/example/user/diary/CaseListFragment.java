package com.example.user.diary;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.06.2016.
 */
public class CaseListFragment extends Fragment {
    public static CaseListFragment create() {
        return new CaseListFragment();
    }

    List<String> ls = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_list_layout, container, false);
        RecyclerView rv = (RecyclerView)v.findViewById(R.id.recycler);
        for(int i = 0; i<20;i++)
            if(i%2!=0)
                ls.add("case "+i);
            else ls.add("case  /nffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff      "+i);


        rv.setAdapter(new Adapter());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        return v;
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
