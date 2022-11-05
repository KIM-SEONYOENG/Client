package com.ograper.runwith.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ograper.runwith.Format.DataResponse;
import com.ograper.runwith.R;
import com.ograper.runwith.network.RetrofitClient;
import com.ograper.runwith.network.UserAPI;
import com.ograper.runwith.service.FCMService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button button;
    private EditText etId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.btnLogin);
        etId = findViewById(R.id.etId);

        Retrofit retrofit = RetrofitClient.getClient();
        UserAPI userAPI = retrofit.create(UserAPI.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                Log.d(TAG, "id = " + id);
                if("".equals(id)){
                    Toast.makeText(getApplicationContext(), "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "id = " + id);
                }
                else{
                    userAPI.userAdd(id).enqueue(new Callback<DataResponse>() {
                        @Override
                        public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                            int result = response.code();
                            Log.d(TAG, "login result = " + result);
                            if(result == 200){
                                Log.d(TAG, "성공!");
                                loginSuccess(id);
                                Log.d(TAG, "id = " + id);
                                return;
                            }
                            else if(result == 300){
                                Toast.makeText(getApplicationContext(),"중복",Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "id = " + id);
                                return;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "id = " + id);
                                return;
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"통신실패",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                }
            }
        });
    }

    public void loginSuccess(String username) {
        Log.d(TAG, "loginSuccess");
        PreferenceAccess preferenceAccess = new PreferenceAccess(getApplicationContext());
        preferenceAccess.setUserName(username);

        if(preferenceAccess.setUserName(username)) {
            Intent fcmIntent = new Intent(getApplicationContext(), FCMService.class);
            startService(fcmIntent);

            finish();
        }
    }
}
