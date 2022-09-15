package com.example.calackids;

import android.app.Application;

import com.example.objects.User;
import com.example.retrofit.FamilyService;
import com.example.retrofit.RetrofitService;
import com.example.retrofit.UserService;

import retrofit2.Retrofit;

public class CalacKidsApplication extends Application {
    private final Retrofit retrofit = new RetrofitService().getRetrofit();

    public final UserService userService = retrofit.create(UserService.class);
    public final FamilyService familyService = retrofit.create(FamilyService.class);


    public User currentUser;
}
