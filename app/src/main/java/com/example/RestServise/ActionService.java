package com.example.RestServise;

import com.example.objects.Action;
import com.example.objects.Invest;
import com.example.objects.Loan;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ActionService {

    @GET("/action/{id}")
    Call<List<Action>> getActions(@Path("id") long userId);

    @POST("/action/save")
    Call<String> saveAction(@Body Action action);

    @POST("/loan/save")
    Call<String> saveLoan(@Body Loan loan);

    @POST("/invest/save")
    Call<String> saveInvest(@Body Invest invest);
}
