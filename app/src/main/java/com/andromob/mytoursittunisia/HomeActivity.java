package com.andromob.mytoursittunisia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.Toast;

import com.andromob.mytoursittunisia.fragment.FragmentContact;
import com.andromob.mytoursittunisia.fragment.FragmentHome;
import com.andromob.mytoursittunisia.fragment.FragmentTravelPack;

import static com.andromob.mytoursittunisia.IntroActivity.MY_PREFS_NAME;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // check intro value either user have seen intro or not
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int intro_value = prefs.getInt("intro_key",0);

        // play intro activity if value is other than 01 svaed in share perefrence
        if(intro_value==01)
        {

        }else
        {
            Intent intent = new Intent(HomeActivity.this, IntroActivity.class);
            startActivity(intent);
        }

       /* if (getIntent().getBooleanExtra("LOGOUT", false))
        {
            finish();
        }*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
            super.onBackPressed();
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
        // automatically handle clicks on the FragmentHome/Up button, so long
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, FragmentHome.newInstance()).commit();
            getSupportActionBar().setTitle(R.string.home);
        }else if(id == R.id.nav_travelpack) {
            HomeActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, FragmentTravelPack.newInstance()).commit();
            getSupportActionBar().setTitle(R.string.travelpack);
        }else if(id == R.id.nav_contacts) {
            HomeActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, FragmentContact.newInstance()).commit();
            getSupportActionBar().setTitle(R.string.contact);
        }else if(id == R.id.nav_chatbot) {
            AppUtils.invokeMessenger(HomeActivity.this);

            Toast.makeText(HomeActivity.this," wait chotbot...",Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
