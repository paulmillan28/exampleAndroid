package com.example.paulgerman.myapplication.Repositories;

import android.util.Log;

import com.example.paulgerman.myapplication.Adapter.MyAdapter;
import com.example.paulgerman.myapplication.ArticuloDataSource;
import com.example.paulgerman.myapplication.Model.Articulo;
import com.example.paulgerman.myapplication.Services.ArticuloService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticuloRepository implements ArticuloDataSource {
    private Retrofit retrofit;

    public  ArticuloRepository(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.70:8084/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    @Override
    public void obtenerArticulos( final articuloCallback callback) {
        ArticuloService restClient = retrofit.create(ArticuloService.class);
        Call<List<Articulo>> call = restClient.obtenerArticulos();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                switch (response.code()) {
                    case 200:
                        List<Articulo> data = response.body();
                        callback.onSuccess(response.body());
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                Log.e("error", t.toString());
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void guardarArticulos(articuloCallback callback) {

    }
}
