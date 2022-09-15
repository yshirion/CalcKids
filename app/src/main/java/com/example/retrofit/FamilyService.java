package com.example.retrofit;

import com.example.objects.Family;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FamilyService {

    @GET("/family/{id}")
    Call<Family> findById(@Path("id") Long id);
}
