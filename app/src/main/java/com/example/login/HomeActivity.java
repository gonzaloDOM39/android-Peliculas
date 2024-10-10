package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.adapters.PeliculaAdapter;
import com.example.login.model.Pelicula;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements PeliculaAdapter.OnPeliculaClickListener {
    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private ArrayList<Pelicula> listaPeliculas;
    private MaterialCardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.reciclerPeliculas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Obtener las películas (simuladas desde el backend)
        listaPeliculas = getPeliculas();

        // Configurar el Adapter
        peliculaAdapter = new PeliculaAdapter(listaPeliculas,HomeActivity.this);
        recyclerView.setAdapter(peliculaAdapter);
    }

 private ArrayList<Pelicula> getPeliculas(){
     ArrayList<Pelicula> peliculas = new ArrayList<>();

     peliculas.add(new Pelicula("The Lion King", "Un joven león huye de su reino.", "https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg"));
     peliculas.add(new Pelicula("Inception", "Un ladrón con la habilidad de entrar en los sueños.", "https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SL1500_.jpg"));
     peliculas.add(new Pelicula("The Matrix", "Un hacker descubre la verdad sobre su mundo.", "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg"));
     peliculas.add(new Pelicula("Finding Nemo", "Un pez payaso busca a su hijo perdido.", "https://m.media-amazon.com/images/I/51H0H4PTBWL._AC_.jpg"));
     peliculas.add(new Pelicula("Avatar", "Un humano se conecta a un cuerpo alienígena.", "https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg"));
     peliculas.add(new Pelicula("The Lion King", "Un joven león huye de su reino.", "https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg"));
     peliculas.add(new Pelicula("Inception", "Un ladrón con la habilidad de entrar en los sueños.", "https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SL1500_.jpg"));
     peliculas.add(new Pelicula("The Matrix", "Un hacker descubre la verdad sobre su mundo.", "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg"));
     peliculas.add(new Pelicula("Finding Nemo", "Un pez payaso busca a su hijo perdido.", "https://m.media-amazon.com/images/I/51H0H4PTBWL._AC_.jpg"));
     peliculas.add(new Pelicula("Avatar", "Un humano se conecta a un cuerpo alienígena.", "https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg"));

     return peliculas;
 }


    @Override
    public void onPeliculaClick(Pelicula pelicula) {
        Intent intent = new Intent(this, PeliculaDetailActivity.class);
        intent.putExtra("pelicula",pelicula);
        Log.i("com.prueba", pelicula.getTitulo());
        startActivity(intent);
    }
}