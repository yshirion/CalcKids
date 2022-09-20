package com.example.calackids;

import android.app.Application;

import com.example.RestServise.ActionService;
import com.example.objects.User;
import com.example.RestServise.FamilyService;
import com.example.RestServise.MessageService;
import com.example.RestServise.RetrofitService;
import com.example.RestServise.UserService;

import retrofit2.Retrofit;

public class CalcKidsApplication extends Application {
    private final Retrofit retrofit = new RetrofitService().getRetrofit();

    public final UserService userService = retrofit.create(UserService.class);
    public final FamilyService familyService = retrofit.create(FamilyService.class);
    public final MessageService messageService = retrofit.create(MessageService.class);
    public final ActionService actionService = retrofit.create(ActionService.class);


    public User currentChildUser;
    public User currentParentUser;

}
