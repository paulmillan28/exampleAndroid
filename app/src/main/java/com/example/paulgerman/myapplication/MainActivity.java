package com.example.paulgerman.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.paulgerman.myapplication.Adapter.MyAdapter;
import com.example.paulgerman.myapplication.Model.Articulo;
import com.example.paulgerman.myapplication.Services.ArticuloService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Articulo> list = new ArrayList<>();
        Articulo a= new Articulo();
        a.setDescripcion("Es la descripcion 1");
        a.setImagen("imagen");
        a.setTipo("lala");
        a.setTitulo("Es el titulo");
        Articulo a1= new Articulo();
        a1.setDescripcion("dos compa");
        a1.setImagen("imagen");
        a1.setTipo("lala");
        a1.setTitulo("titulo dos compa");
        list.add(a);
        list.add(a1);
        mAdapter = new MyAdapter(list);
        recyclerView.setAdapter(mAdapter);
        loadJSON();
    }

    private void loadJSON(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.70:8084/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ArticuloService restClient = retrofit.create(ArticuloService.class);
        Call<List<Articulo>> call = restClient.obtenerArticulos();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                switch (response.code()) {
                    case 200:
                        List<Articulo> data = response.body();
                        mAdapter = new MyAdapter(data);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
