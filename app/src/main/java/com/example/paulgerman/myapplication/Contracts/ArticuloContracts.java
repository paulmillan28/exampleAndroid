package com.example.paulgerman.myapplication.Contracts;

import com.example.paulgerman.myapplication.Base.BasePresenter;
import com.example.paulgerman.myapplication.Base.BaseView;
import com.example.paulgerman.myapplication.Model.Articulo;

import java.util.List;

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
