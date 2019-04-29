package com.example.paulgerman.myapplication.Services;

import com.example.paulgerman.myapplication.Model.Articulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by PaulGerman on 4/28/19.
 */

public interface ArticuloService {
    @GET("v1/articulo")
    Call<List<Articulo>> obtenerArticulos();

    @POST("v1/articulo")
    Call<Void> guardarArticulo(@Body Articulo articulo);

}
