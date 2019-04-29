package com.example.paulgerman.myapplication.Data;

import com.example.paulgerman.myapplication.Model.Articulo;

import java.util.List;

public interface ArticuloDataSource {

    void obtenerArticulos(
            ArticuloDataSource.articuloCallback callback);

    void guardarArticulos(
                ArticuloDataSource.articuloCallback callback);

    interface articuloCallback {
        void onSuccess(List<Articulo> articulos);

        void onFailed(String error);
    }
}
