package com.ograper.runwith.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ograper.runwith.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PreferenceAccess preferenceAccess = new PreferenceAccess();
//        if(preferenceAccess.getID==null) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        }

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("FRIEND"));
        tabLayout.addTab(tabLayout.newTab().setText("MY PAGE"));

        getSupportFragmentManager().beginTransaction().add(homeFragment, "HOME").commit();
    }
}