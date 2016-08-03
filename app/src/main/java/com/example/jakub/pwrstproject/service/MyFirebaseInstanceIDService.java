package com.example.jakub.pwrstproject.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.example.jakub.pwrstproject.util.DatabaseHelper;

/**
 * Created by Jakub on 02.08.2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.updateUserToken(refreshedToken);
    }



}
