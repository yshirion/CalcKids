package com.example.RestServise;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
//Class of retrofit manage the network.
public class RetrofitService {

    private Retrofit retrofit;
    private static RetrofitService instance = null;

    public RetrofitService() {
        initializeRetrofit();
    }


    // For deserialize the date from the server.
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
        }
    }).setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).create();

    //    Gson gson = new GsonBuilder()
    //            .setPrettyPrinting()
    //            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
    //            .create();

    //initialize the finally object.
    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.2:9090/")
                .addConverterFactory(ScalarsConverterFactory//For getting String from server.
                        .create())
                .addConverterFactory(GsonConverterFactory//For getting Localdate from server(parse string to object).
                        .create(gson))                      //and to send date object to the server.
//                .addConverterFactory(GsonConverterFactory
//                        .create(gson))
                .addConverterFactory(GsonConverterFactory
                .create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
