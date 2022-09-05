package com.ograper.runwith.view;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.ograper.runwith.R;
import com.ograper.runwith.service.FCMService;
import com.ograper.runwith.service.StepManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private static final String TAG = "MainActivity";

    private PreferenceAccess preferenceAccess;

    private TabLayout tabLayout;
    private HomeFragment homeFragment = new HomeFragment();
    private FriendFragment friendFragment = new FriendFragment();
    private TeamFragment teamFragment = new TeamFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceAccess = new PreferenceAccess(getApplicationContext());
        Log.d(TAG, preferenceAccess.getUserName());
        if(preferenceAccess.getUserName().equals("") || preferenceAccess.getUserName()==null) {
            preferenceAccess.setStep(0);
            startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 0);
        }

        Log.d(TAG, "FCM Service is Running = " + isMyServiceRunning(FCMService.class));


//        startService(new Intent(MainActivity.this, StepManager.class));

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu_btn1));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu_btn2));
        tabLayout.addTab(tabLayout.newTab().setText("MY PAGE"));

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0 :
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit(); break;
            case 1 :
                if(preferenceAccess.isTeam()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, teamFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, friendFragment).commit();
                }
                break;
            case 2 :
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, myPageFragment).commit(); break;
        }
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}
    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    private boolean isMyServiceRunning(Class<?> serviceClass)
    {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}