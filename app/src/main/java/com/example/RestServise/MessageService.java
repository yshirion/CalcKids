package com.example.RestServise;

import com.example.objects.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageService {

    @POST("message/save")
    Call<String> saveMessage(@Body Message message);
}
