package com.example.login.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.model.Pelicula;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private List<Pelicula> peliculas;

    public PeliculaAdapter(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelicula_item, parent, false);
        return new PeliculaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);

        holder.titulo.setText(pelicula.getTitulo());
        holder.descripcion.setText(pelicula.getDescripcion());

        // Usar Glide para cargar la imagen desde la URL
        Glide.with(holder.itemView.getContext())
                .load(pelicula.getImagenUrl())
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion;
        ImageView imagen;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.PTitulo);
            descripcion = itemView.findViewById(R.id.PDescripcion);
            imagen = itemView.findViewById(R.id.PImagen);
        }
    }
}