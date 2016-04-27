package com.swe.zonein.zonein.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.swe.zonein.zonein.Adapters.CheckinAdapter;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar ;
    FragmentManager fragmentManager = getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Home");
        fragmentManager
                .beginTransaction()
                .replace(R.id.view_content, HomePageFragment.newInstance(),
                        "FRAGMENT").addToBackStack(null).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
         if (getFragmentManager().getBackStackEntryCount() == 0) {
                this.finish();
            } else {
                getFragmentManager().popBackStack();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            toolbar.setTitle("Home");
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id == R.id.nav_all_places) {
            toolbar.setTitle("All Places");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, AllPlacesFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();


        } else if (id == R.id.nav_check_in) {
            toolbar.setTitle("Check in");

        } else if (id == R.id.nav_People_i_follow) {
            toolbar.setTitle("Following");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, FollowingFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();

            //toolbar.setTitle("People I Follow");
            //Intent intent = new Intent(MainActivity.this, FollowingActivity.class);
            //startActivity(intent);
        } else if (id == R.id.nav_people_following_me) {
            toolbar.setTitle("Followers");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, FollowersFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();

            //Intent intent = new Intent(MainActivity.this, FollowersActivity.class);

            //startActivity(intent);
       //     finish();
        } else if (id == R.id.nav_all_users) {
            toolbar.setTitle("All Users");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, AllUsersFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();

        } else if (id == R.id.nav_add_place) {
            toolbar.setTitle("Add Place");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, AddPlaceFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();

        }
        else if (id == R.id.nav_notifications) {
            toolbar.setTitle("Notifications");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, NotificationsFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();
        }
        else if (id == R.id.nav_saved_places) {
            toolbar.setTitle("Saved Places");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_content, SavedPlacesFragment.newInstance(),
                            "FRAGMENT").addToBackStack(null).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
