package com.onesoul.moviecataloguefinal.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onesoul.moviecataloguefinal.MainViewModel;
import com.onesoul.moviecataloguefinal.R;
import com.onesoul.moviecataloguefinal.movie.Movie;

import java.util.ArrayList;

public class MovieFavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private MovieFavoriteAdapter movieFavoriteAdapter;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favorite, container, false);
        recyclerView = view.findViewById(R.id.rv_movie_favorite_fragment);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMoviesFavorite("movie").observe(getViewLifecycleOwner(), getMovieFavorite);

        movieFavoriteAdapter = new MovieFavoriteAdapter(getActivity());
        movieFavoriteAdapter.notifyDataSetChanged();
        mainViewModel.setMovieDatabase("movie");
        showRecyclerList();
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(movieFavoriteAdapter);
    }

    private final Observer<ArrayList<Movie>> getMovieFavorite = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                movieFavoriteAdapter.setListMovieFavorite(movies);
                showRecyclerList();
            }
        }
    };

    public void onResume() {
        super.onResume();
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setMovieDatabase("movie");
    }
}
