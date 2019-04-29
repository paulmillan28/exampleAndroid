package com.example.paulgerman.myapplication;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.paulgerman.myapplication.Adapter.MyAdapter;
import com.example.paulgerman.myapplication.Contracts.ArticuloContracts;
import com.example.paulgerman.myapplication.Model.Articulo;
import com.example.paulgerman.myapplication.Contracts.ArticuloContracts;
import com.example.paulgerman.myapplication.Repositories.ArticuloRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements ArticuloContracts.View{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final String TAG = getClass().getSimpleName();
    ProgressDialog progressDialog;
    private ArticuloContracts.Presenter mPresenter;
    private ArticuloPresenter articuloPresenter =
            new ArticuloPresenter(
                    this,
                    new ArticuloRepository());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this, R.style.AppTheme);
        ArticuloPresenter articuloPresenter =
                new ArticuloPresenter(
                        this,
                        new ArticuloRepository());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Articulo articulo  = new Articulo();
                articulo.setDescripcion("");
                articulo.setTipo("");
                articulo.setImagen("");
                articulo.setTitulo("");
                mPresenter.guardarArticulo(articulo);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mPresenter.obtenerArticulos();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void loadJSON(List<Articulo> data){
        mAdapter = new MyAdapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        View view =  getWindow().getDecorView().getRootView();
        if ( view != null) {
            Snackbar snackbar = Snackbar.make( view , mensaje, Snackbar.LENGTH_SHORT);
            View snackbarView = snackbar.getView();
            TextView textView =
                    (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();
        }
    }

    @Override
    public void mostrarDialogo(String mensaje) {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(mensaje);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        try {
            progressDialog
                    .getWindow()
                    .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } catch (Exception e) {
            Log.e(TAG + ":getProgressDialog", e.toString());
        }
        progressDialog.show();
    }

    @Override
    public void ocultarDialogo() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setPresenter(ArticuloContracts.Presenter presenter) {
        mPresenter=presenter;
    }
}
