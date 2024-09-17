
package com.example.login.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.login.R;
import com.example.login.adapters.ImageCarouselAdapter;


import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MovieCarouselFragment extends Fragment {

    private ViewPager2 viewPager;

    private final int SLIDE_DELAY = 3000; // 5 segundos
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_carousel, container, false);

        viewPager = view.findViewById(R.id.viewPager);

        // Lista de imágenes (usar referencias a drawables)
        List<Integer> images = Arrays.asList(
                R.drawable.github,
                R.drawable.estrella,
                R.drawable.instagram
        );

        // Configurar el adaptador
        ImageCarouselAdapter adapter = new ImageCarouselAdapter(getContext(), images);
        viewPager.setAdapter(adapter);


        startAutoSlide();
        return view;
    }
    private void startAutoSlide() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (viewPager != null) {
                    int currentItem = viewPager.getCurrentItem();
                    int totalItems = viewPager.getAdapter().getItemCount();
                    if (currentItem < totalItems - 1) {
                        viewPager.setCurrentItem(currentItem + 1, true);
                    } else {
                        viewPager.setCurrentItem(0, true);
                    }
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 0, SLIDE_DELAY);
    }
}
