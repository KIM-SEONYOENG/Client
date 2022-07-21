package com.ograper.runwith.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ograper.runwith.R;
import com.ograper.runwith.service.FCMService;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginSuccess("tester");
    }

    public void loginSuccess(String username) {
        PreferenceAccess preferenceAccess = new PreferenceAccess(getApplicationContext());
        preferenceAccess.setUserName(username);

        Intent fcmIntent = new Intent(getApplicationContext(), FCMService.class);
        startService(fcmIntent);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
