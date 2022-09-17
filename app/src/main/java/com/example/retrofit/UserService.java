package com.example.retrofit;

import com.example.objects.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("user/{id}")
    Call<User> getById(@Path("id") String userId);

    @GET("user/check/{name}/{password}")
    Call<User> checkUser(@Path("name") String name, @Path("password") String password);

    @POST("user/save")
    Call<String> save(@Body User user);

    @POST("user/saveParent")
    Call<String> saveParent(@Body User user);
}
