package com.example.jakub.pwrstproject.retrofit;

import com.example.jakub.pwrstproject.PWrSTApplication;
import com.example.jakub.pwrstproject.model.FCMMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class RestAdapter {

    public static final String BASE_URL = "https://test-1a137.firebaseio.com/";    //Mozliwe ze to nie ten address co trzeba
    private final RestApi mApiManager;

    public RestAdapter() {
        mApiManager = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RestApi.class);
    }

    public void sendPush(String to) {
        mApiManager.sendPush(new FCMMessage(to, PWrSTApplication.getInstance().getPreferences().getUsername())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(final Call<Void> call, final Response<Void> response) {
                //no-op
            }

            @Override
            public void onFailure(final Call<Void> call, final Throwable t) {
                //no-op
            }
        });
    }

    public interface RestApi {
        @Headers("Authorization: key=AIzaSyAGQsEOkpkcyLy0r_oZtW51QJ2bGPJSp2Y")   //bankowo dobry
        @POST("send")
        Call<Void> sendPush(@Body FCMMessage message);
        ///
    }
}
