package com.example.activities;

import android.app.IntentService;
import android.content.Intent;

import com.example.calackids.CalcKidsApplication;
import com.example.objects.Family;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class BackgroundService  extends IntentService {
    CalcKidsApplication app;
    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        app = (CalcKidsApplication) getApplication();
        Family family = new Family();
        Call<Family> call = app.familyService.findById((long)5);
        try {
            Response<Family> r = call.execute();
            System.out.println("IN");
        }
        catch (IOException e){
            System.out.println("OUT");

        }
    }

}
