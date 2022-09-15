package com.example.retrofit;

import com.example.objects.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("user/{id}")
    Call<User> getById(@Path("id") String userId);

    @POST("user")
    Call<User> save(@Body User user);
}
