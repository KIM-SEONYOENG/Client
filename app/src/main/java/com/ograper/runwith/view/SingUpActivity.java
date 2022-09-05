package com.ograper.runwith.view;


import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.ograper.runwith.R;

public class SingUpActivity extends AppCompatActivity {

    private CheckBox chk_str1, chk_str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        chk_str1 = findViewById(R.id.chk_str1);
        chk_str2 = findViewById(R.id.chk_str2);

        if(chk_str1.isChecked()&&chk_str2.isChecked()){

        }
    }
}