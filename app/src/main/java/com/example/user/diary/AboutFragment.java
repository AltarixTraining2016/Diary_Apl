package com.example.user.diary;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;


/**
 * Created by User on 03.06.2016.
 */
public class AboutFragment extends Fragment implements Titleable{

    public static AboutFragment create() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_layout, container, false);

        Button b = (Button)v.findViewById(R.id.button_db);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DataBaseActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    */

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.nav_about);
    }
}
