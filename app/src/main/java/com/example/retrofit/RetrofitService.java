package com.example.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private static RetrofitService instance = null;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.2:9090/")
                .addConverterFactory(ScalarsConverterFactory
                        .create())
                .addConverterFactory(GsonConverterFactory
                .create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .build();
    }
    public static synchronized RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
