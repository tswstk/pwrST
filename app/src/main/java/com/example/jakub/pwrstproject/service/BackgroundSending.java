package com.example.jakub.pwrstproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.jakub.pwrstproject.Constants;
import com.example.jakub.pwrstproject.PWrSTApplication;
import com.example.jakub.pwrstproject.model.User;
import com.example.jakub.pwrstproject.util.DatabaseHelper;

/**
 * Created by Jakub on 02.08.2016.
 */
public class BackgroundSending extends Service {
    @Nullable
    @Override


    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        String address = "";

        Bundle extras = intent.getExtras();

        address = extras.getString(Constants.USER_TARGET);

        PWrSTApplication.getInstance().getDatabaseHelper().getUserFromDatabase(address, new DatabaseHelper.OnUserReceivedListener() {
            @Override
            public void onUserReceived(User user) {
                PWrSTApplication.getInstance().getRestAdapter().sendPush(user.getToken());
            }
        });



        PWrSTApplication.getInstance().getRestAdapter().sendPush(address);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
