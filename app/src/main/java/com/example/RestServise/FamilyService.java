package com.example.RestServise;

import com.example.objects.Family;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FamilyService {

    @GET("/family/{id}")
    Call<Family> findById(@Path("id") Long id);

    @POST("/family/update")
    Call<Family> update(@Body Family family);
}
