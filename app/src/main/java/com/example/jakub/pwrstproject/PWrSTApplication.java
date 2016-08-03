package com.example.jakub.pwrstproject;

import android.app.Application;
import com.example.jakub.pwrstproject.retrofit.RestAdapter;
import com.example.jakub.pwrstproject.util.DatabaseHelper;

public class PWrSTApplication extends Application {

    private static PWrSTApplication sInstance;

    private PWrSTPreferences mPWrSTPreferences;
    private RestAdapter mRestAdapter;
    private DatabaseHelper mDatabaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mPWrSTPreferences = new PWrSTPreferences(this);
        mRestAdapter = new RestAdapter();
        mDatabaseHelper = new DatabaseHelper();
    }

    public static PWrSTApplication getInstance() {
        return sInstance;
    }

    public PWrSTPreferences getPreferences() {
        return mPWrSTPreferences;
    }

    public RestAdapter getRestAdapter() {
        return mRestAdapter;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }
}
