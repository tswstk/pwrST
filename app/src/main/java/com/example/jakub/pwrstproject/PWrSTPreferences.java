package com.example.jakub.pwrstproject;

import android.content.Context;
import android.content.SharedPreferences;

public class PWrSTPreferences {

    SharedPreferences sharedPreferences;

    public PWrSTPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.USERNAME_SHAREDPREF ,Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USERNAME_LOGIN, username);
        editor.apply();
    }

    public String getUsername() {
        String userName = sharedPreferences.getString(Constants.USERNAME_LOGIN, Constants.USERNAME_DEFAULT);
        return userName;
    }



}
