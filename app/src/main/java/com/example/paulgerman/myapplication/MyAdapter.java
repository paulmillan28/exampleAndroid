package com.example.paulgerman.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PaulGerman on 4/28/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Articulo> mDataset;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView description;

        public MyViewHolder(View v) {
            super(v);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
        }
    }

    public MyAdapter( List<Articulo> articulos) {
        mDataset = articulos;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitulo());
        holder.description.setText(mDataset.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}