package com.example.login.adapters;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.login.databinding.PeliculaItemBinding;
import com.example.login.model.Pelicula;
import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {
    private List<Pelicula> peliculas;
    private OnPeliculaClickListener listener;

    // Interfaz para manejar el clic en el ítem de película
    public interface OnPeliculaClickListener {
        void onPeliculaClick(Pelicula pelicula);
    }

    // Constructor del adapter
    public PeliculaAdapter(List<Pelicula> peliculas, OnPeliculaClickListener listener) {
        this.peliculas = peliculas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inicializar el binding con el layout del ítem
        PeliculaItemBinding binding = PeliculaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PeliculaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);

        // Usar el binding para acceder a los componentes del layout
        holder.binding.PTitulo.setText(pelicula.getTitulo());

        // Usar Glide para cargar la imagen desde la URL
        Glide.with(holder.itemView.getContext())
                .load(pelicula.getImagenUrl())
                .into(holder.binding.PImagen);

        // Configurar el clic en el ítem de película
        holder.binding.card.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPeliculaClick(pelicula);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    // ViewHolder para manejar los componentes con ViewBinding
    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        private final PeliculaItemBinding binding;

        public PeliculaViewHolder(@NonNull PeliculaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
