package com.uts.findmeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainNavi_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView username;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_main);

        final Bundle dat = getIntent().getExtras();
        String data = data = dat.getString("key");
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new Fragment_Home());
        fragmentTransaction.commit();
        username = navigationView.getHeaderView(0).findViewById(R.id.user_usrname);

        username.setText(data);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        drawerLayout.closeDrawer(GravityCompat.START);
        Bundle dat = getIntent().getExtras();
        String data = dat.getString("key");;
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Fragment_Home());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.testimoni){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Fragment_Testimoni());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.donasi){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Fragment_Donasi());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.umpan_balik){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Fragment_FeedBack());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.tentang_kami){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Fragment_AboutUs());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.bantuan){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Fragment_Bantuan());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.logout){
            Intent intent = new Intent(this, PageAwal_Activity.class);
            startActivity(intent);
        }
        if(menuItem.getItemId() == R.id.profile1){
            Intent intent = new Intent(this, Profile_Activity.class);
            intent.putExtra("key",data);
            startActivity(intent);
        }
        return true;
    }

}
