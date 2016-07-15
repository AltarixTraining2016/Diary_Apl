package com.example.user.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.name_case_layout, container, false);

        RecyclerView rv = (RecyclerView)v.findViewById(R.id.rec_name_case);

        cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT name FROM table_list_name_case", null);
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
                int id;
                String idd = "";

                if(!st.isEmpty()){
                    cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id FROM table_list_name_case", null);
                    if (cursor.moveToFirst()) {
                        cursor.moveToLast();
                        id = cursor.getInt(0);
                        id++;
                        idd = String.valueOf(id);
                    } else {
                        Log.w("Sah", "empty!");
                    }
                    cursor.close();

                    ContentValues cv = new ContentValues();
                    cv.put("_id", idd);
                    cv.put("name", st);
                    DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);

                    ls.add(st);
                }

                et.setText("");
            }
        });

        return v;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_name_Case);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        TextView tv;
        //LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            //ll = (LinearLayout) itemView.findViewById(R.id.ll);
            tv = (TextView) itemView.findViewById(R.id.textViewRecyclerNameCase);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = this.getLayoutPosition();
            showPopupMenu(v,position);
            return false;
        }

        private void showPopupMenu(View v, final int position) {
            PopupMenu popupMenu = new PopupMenu(getContext(), v);
            popupMenu.inflate(R.menu.popupmenu);

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Toast.makeText(PopupMenuDemoActivity.this,
                            // item.toString(), Toast.LENGTH_LONG).show();
                            // return true;
                            switch (item.getItemId()) {

                                case R.id.menu1:
                                    //Toast.makeText(getContext(),"Вы выбрали PopupMenu 1",Toast.LENGTH_SHORT).show();
                                    String id = "";
                                    int k = 0;

                                    Cursor cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT * FROM table_list_name_case", null);
                                    if (cursor.moveToFirst()) {
                                        cursor.moveToPosition(position);
                                        id = cursor.getString(0);
                                    } else {
                                        Log.w("Sah", "empty!");
                                    }
                                    cursor.close();

                                    int n_id = 0;
                                    cursor = DataBaseHelper.getInstance().getWritableDatabase().rawQuery("SELECT _id,name_id FROM table_list_case", null);
                                    if (cursor.moveToFirst()) {
                                        while (!cursor.isAfterLast()) {
                                            //n_id = ;
                                            if(cursor.getInt(1)==Integer.valueOf(id))//cursor.getString(1).equals(id))
                                                k++;
                                            cursor.moveToNext();
                                        }

                                    } else {
                                        Log.w("Sah", "empty!");
                                    }
                                    cursor.close();





                                    Log.v("Sah", "delete " + id);
                                    try{
                                        if(k==0)
                                            DataBaseHelper.getInstance().getWritableDatabase().delete("table_list_name_case", "_id = ?", new String[]{id});
                                        else{
                                            Toast t = Toast.makeText(getContext(),"На данный элемент есть ссылка!", Toast.LENGTH_SHORT);
                                            t.show();
                                        }

                                        NameCaseFragment fragment = new NameCaseFragment();
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_content, fragment).commit();
                                        if (fragment instanceof Titleable) {
                                            String title = ((Titleable) fragment).getTitle(getActivity());
                                            getActivity().setTitle(title);
                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        Toast toast = Toast.makeText(getContext(),"На данный элемент есть ссылка!", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                    return true;

                                default:
                                    return false;
                            }
                        }
                    });

            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu menu) {
                    //Toast.makeText(getContext(), "onDismiss",Toast.LENGTH_SHORT).show();
                }
            });
            popupMenu.show();
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
