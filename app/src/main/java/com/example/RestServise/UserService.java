package com.example.RestServise;

import com.example.objects.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("user/{id}")
    Call<User> getById(@Path("id") long userId);

    //for message
    @GET("user/parent/{fid}")
    Call<User> getParent(@Path("fid") long fid);

    //get all children for menu of parent and send message
    @GET("user/family/{id}")
    Call<List<User>> findByFamily(@Path("id") long family_id);

    @GET("user/changeParent/{id}")
    Call<String> changeToParent(@Path("id") long id);

    @GET("user/check/{name}/{password}")
    Call<User> checkUser(@Path("name") String name, @Path("password") String password);

    @POST("user/save")
    Call<String> save(@Body User user);

    @POST("user/saveParent")
    Call<String> saveParent(@Body User user);

    @HTTP(method = "DELETE", path = "/user/deleteUser", hasBody = true)
    Call<String> deleteUser(@Body User user);
}
