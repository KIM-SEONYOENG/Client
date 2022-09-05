package com.ograper.runwith.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ograper.runwith.Format.DataResponse;
import com.ograper.runwith.Format.TokenEntity;
import com.ograper.runwith.network.TokenAPI;
import com.ograper.runwith.network.RetrofitClient;
import com.ograper.runwith.view.MainActivity;
import com.ograper.runwith.view.PreferenceAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FCMService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingService";

    private static final String CHANNEL_ID = "NOTIFICATION";
    private static final String CHANNEL_NAME = "FCM";



    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "new Token : " + token);
        PreferenceAccess preferenceAccess = new PreferenceAccess(getApplicationContext());
        sendToken(preferenceAccess.getUserName(), token);
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);

        if(message.getData().size() > 0) {
            showMessageNotification(message.getData().get("id"), message.getData().get("message"));
        }
    }

    public void showMessageNotification(String id, String msg) {
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(FCMService.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                .setSmallIcon()
                .setContentTitle(id)
                .setContentText(msg)
                .setVibrate(new long[] {1000})
                .setContentIntent(pendingIntent);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, builder.build());
    }

    public void sendToken(String id, String newToken) {
        Log.d(TAG, "send Token : " + id + " is " + newToken);
        Retrofit retrofit = RetrofitClient.getClient();
        TokenAPI messageAPI = retrofit.create(TokenAPI.class);
        messageAPI.sendToken(id, newToken).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                DataResponse dataResponse = new DataResponse();
                if(dataResponse.getResponseCode()==200) {
                    Log.d(TAG, "Insert Success!");
                } else if(dataResponse.getResponseCode()==300) {
                    Log.d(TAG, "Connection Success, Insert Failed!");
                }
                Log.d(TAG, "Connected");
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d(TAG, "Connection Failed!");
            }
        });
    }
}
