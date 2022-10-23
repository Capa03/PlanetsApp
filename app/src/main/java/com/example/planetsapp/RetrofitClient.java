package com.example.planetsapp;

import java.util.FormatFlagsConversionMismatchException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://my-json-server.typicode.com/carlossancho-pt/planets/";

    private static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PlanetsService getService() {
        return getRetrofitInstance().create(PlanetsService.class);
    }
}
