package com.example.RestServise;

import com.example.objects.Action;
import com.example.objects.Family;
import com.example.objects.Invest;
import com.example.objects.Loan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ActionService {

    @POST("/action/save")
    Call<String> saveAction(@Body Action action);

    @POST("/action/loan/save")
    Call<String> saveLoan(@Body Loan loan);

    @POST("/action/invest/save")
    Call<String> saveInvest(@Body Invest invest);
}
