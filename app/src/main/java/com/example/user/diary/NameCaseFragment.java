package com.example.user.diary;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.06.2016.
 */
public class NameCaseFragment extends Fragment implements Titleable{
    public static NameCaseFragment create() {
        return new NameCaseFragment();
    }

    List<String> ls = new ArrayList<>();
    EditText et;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.name_case_layout, container, false);

        RecyclerView rv = (RecyclerView)v.findViewById(R.id.rec_name_case);

        Cursor cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name FROM table_list_name_case", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ls.add(cursor.getString(0));
                cursor.moveToNext();
            }
        } else {
            Log.w("Sah", "empty!");
        }
        cursor.close();

        rv.setAdapter(new Adapter());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab_name_case);
        Button bt = (Button)v.findViewById(R.id.button_add_name_case);
        et = (EditText)v.findViewById(R.id.editText_name_case);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String st = et.getText().toString();
                if(st!="")
                    ls.add(st);
                et.setText("");
            }
        });

        return v;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_name_Case);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        //LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            //ll = (LinearLayout) itemView.findViewById(R.id.ll);
            tv = (TextView) itemView.findViewById(R.id.textViewRecyclerNameCase);
        }
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //return new ViewHolder(parent);
            //return new ViewHolder(View.inflate(MainActivity.this, R.layout.listitem_card, null));
            return new ViewHolder(View.inflate(getContext(), R.layout.listitem_card_name, null));
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
