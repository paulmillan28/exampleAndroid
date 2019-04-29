package com.example.paulgerman.myapplication.Contracts;

import com.example.paulgerman.myapplication.BasePresenter;
import com.example.paulgerman.myapplication.BaseView;
import com.example.paulgerman.myapplication.Model.Articulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PaulGerman on 4/28/19.
 */

public interface ArticuloContracts {
    interface View extends BaseView<Presenter> {

        void mostrarMensaje(String mensaje);

        void mostrarDialogo(String mensaje);

        void ocultarDialogo();

        void loadJSON(List<Articulo> articulos);

    }

    interface Presenter extends BasePresenter {
        void guardarArticulo(Articulo articulo);

        void obtenerArticulos();
    }
}
