package com.example.ashenone.woke;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        HomeFragment fragment = new HomeFragment();                                     //defaults to HomeFragment at the start
        Bundle bundle = new Bundle();
        bundle.putString("TIME", "ded");
        bundle.putString("DAY", "getsuyoubi");
        fragment.setArguments(bundle);
        loadFragment(fragment);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        toolbar.setTitle("Home");

                        HomeFragment homeFragment = new HomeFragment();                       //put data to show here
                        Bundle homeBundle = new Bundle();
                        homeBundle.putString("TIME", "ded");
                        homeBundle.putString("DAY", "getsuyoubi");

                        homeFragment.setArguments(homeBundle);
                        loadFragment(homeFragment);
                        return true;
                    case R.id.nav_music:
                        toolbar.setTitle("Music");

                        MusicFragment musicFragment = new MusicFragment();                       //put data to show here
                        Bundle musicBundle = new Bundle();
                        musicBundle.putString("TITLE", "Catch the Moment");

                        musicFragment.setArguments(musicBundle);
                        loadFragment(musicFragment);
                        return true;
                    case R.id.nav_user:
                        toolbar.setTitle("User");

                        UserFragment userFragment = new UserFragment();                       //put data to show here
                        Bundle userBundle = new Bundle();
                        userBundle.putString("USERNAME", "Emiru");

                        userFragment.setArguments(userBundle);
                        loadFragment(userFragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
