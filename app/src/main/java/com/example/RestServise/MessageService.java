package com.example.RestServise;

import com.example.objects.Message;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageService {

    @GET("message/to/{id}")
    Call<List<Message>> getMessageByDest(@Path("id") long id);

    @POST("message/update")
    Call<String> updateIsRead(@Body List<Message> messages);

    @POST("message/save")
    Call<String> saveMessage(@Body Message message);

}
