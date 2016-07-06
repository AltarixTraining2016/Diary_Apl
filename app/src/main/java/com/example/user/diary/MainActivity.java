package com.example.user.diary;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentTransaction ft;
    Bundle bundle;
    SimpleDateFormat dateFormat;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;


    //List<String> ls = new ArrayList<>();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        replaceContent(CaseListFragment.create());

        setTables();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

       // if (drawerToggle.onOptionsItemSelected(item)) {
       //     return true;
       // }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.nav_main:
                replaceContent(CaseListFragment.create());
                b = true;
                break;
            case R.id.nav_week:
                replaceContent(WeekFragment.create());
                b = true;
                break;
            case R.id.nav_calendar:
                replaceContent(CalendarFragment.create());
                b = true;
                break;
            case R.id.nav_neme_Case:
                replaceContent(NameCaseFragment.create());
                b = true;
                break;
            case R.id.nav_about:
                replaceContent(AboutFragment.create());
                b = true;
                break;
            default:
                b = false;
                break;
        }
        if (b) {
            drawer.closeDrawers();
        }
        return b;

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        //return true;
    }

    private void replaceContent(Fragment fragment) {
        //
        //ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.container_content, fragment);
        //ft.commit();
        bundle = new Bundle();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        bundle.putString("DATA_CASE_LIST",dateFormat.format(new Date()));
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_content, fragment).commit();
        if (fragment instanceof Titleable) {
            String title = ((Titleable) fragment).getTitle(this);
            setTitle(title);
        }
    }


    public void setTables(){
        ContentValues cv = new ContentValues();
        cv.put("_id", "1");  cv.put("name", "универ");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "2");  cv.put("name", "курсы");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "3");  cv.put("name", "ино");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "4");  cv.put("name", "магазин");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
        cv.put("_id", "5");  cv.put("name", "гулять");
        DataBaseHelper.getInstance().getWritableDatabase().insert("table_list_name_case", null, cv);
    }



}
