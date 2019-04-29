package com.example.paulgerman.myapplication;

import com.example.paulgerman.myapplication.Services.ArticuloService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PaulGerman on 4/28/19.
 */

public class service {

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.70:8084/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    ArticuloService restClient = retrofit.create(ArticuloService.class);
}
