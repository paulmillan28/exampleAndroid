package com.example.paulgerman.myapplication.presenter;

import com.example.paulgerman.myapplication.Data.ArticuloDataSource;
import com.example.paulgerman.myapplication.Contracts.ArticuloContracts;
import com.example.paulgerman.myapplication.Model.Articulo;
import com.example.paulgerman.myapplication.Repositories.ArticuloRepository;

import java.util.List;

public class ArticuloPresenter implements ArticuloContracts.Presenter {

    private ArticuloRepository mRepository;
    private ArticuloContracts.View mView;

    public ArticuloPresenter(
            ArticuloContracts.View view, ArticuloRepository repository) {

        mView = view;
        mRepository = repository;
        mView.setPresenter(this);
    }

    @Override
    public void guardarArticulo(Articulo articulo) {
        mRepository.guardarArticulos(articulo, new ArticuloDataSource.guardarArticuloCallback() {
            @Override
            public void onSuccess() {
                mView.ocultarDialogo();
                mView.mostrarMensaje("Se guardó correctamente.");
            }

            @Override
            public void onFailed(String error) {
                mView.ocultarDialogo();
                mView.mostrarMensaje("Ocurrió un error.");
            }
        });
    }

    @Override
    public void obtenerArticulos() {
        mRepository.obtenerArticulos(new ArticuloDataSource.articuloCallback() {
            @Override
            public void onSuccess(List<Articulo> articulos) {
                mView.ocultarDialogo();
                mView.loadJSON(articulos);
            }

            @Override
            public void onFailed(String error) {
                mView.ocultarDialogo();
                mView.mostrarMensaje(error);
            }
        });

    }
}