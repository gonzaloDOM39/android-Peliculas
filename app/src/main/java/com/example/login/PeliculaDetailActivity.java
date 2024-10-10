package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.login.adapters.PeliculaAdapter;
import com.example.login.databinding.PeliculaDetailBinding;
import com.example.login.model.Pelicula;

public class PeliculaDetailActivity extends AppCompatActivity {
    private PeliculaDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PeliculaDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();


        // Verificar si el Intent contiene el objeto 'Pelicula'
        if (intent != null && intent.hasExtra("pelicula")) {
            Pelicula pelicula = (Pelicula) intent.getSerializableExtra("pelicula");
            Log.i("com.prueba", pelicula.getTitulo());
            if (pelicula != null) {
                binding.movieTitle.setText(pelicula.getTitulo());

                // Usar Glide para cargar la imagen desde la URL
                Glide.with(this)
                        .load(pelicula.getImagenUrl())
                        .into(binding.backgroundImage);
            }
        }
    }
}
