package com.example.paulgerman.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


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
    }
}
