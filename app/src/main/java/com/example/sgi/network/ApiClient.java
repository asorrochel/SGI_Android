package com.example.sgi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


}
