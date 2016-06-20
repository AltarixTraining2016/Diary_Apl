package com.example.user.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 19.06.2016.
 */
public class MainFragment extends Fragment {

    public static MainFragment create() {
        return new MainFragment();
    }


    RecyclerView rv;// = findViewById(R.id.recycleViewMain);
    //LinearLayoutManager llman = new LinearLayoutManager(getContext());
    List<String> ls = new ArrayList<>();
    //Adapter adp = new Adapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_layout, container, false);
        rv = (RecyclerView)rv.findViewById(R.id.recycleViewMain);

        for(int i = 0; i<10;i++)
            ls.add("case "+i);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new Adapter());
        return v;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.itemMain);
        }
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //return new ViewHolder(parent);
            return new ViewHolder(View.inflate(getContext(), R.layout.item_main_layout, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(ls.get(position));
        }

        @Override
        public int getItemCount() {
            return ls.size();
        }
    }


}
