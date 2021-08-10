package com.example.tmdb.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.tmdb.R;
import com.example.tmdb.adapter.MovieAdapter;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MoviesServerResponse;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("TMDB Popular Movies");

        getPopularMovies();
    }

    private void getPopularMovies() {

        MovieDataService moviesData = RetrofitInstance.getRetrofit();

        Call<MoviesServerResponse> popularMovies = moviesData.getPopularMovies(this.getString(R.string.api_key));
        popularMovies.enqueue(new Callback<MoviesServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesServerResponse> call, @NonNull Response<MoviesServerResponse> response) {
                //Success - Display Movies

                MoviesServerResponse body = response.body();
                if (body.getMovies() != null)
                {
                    movies = (ArrayList<Movie>) body.getMovies();

                    showInRecyclerView();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesServerResponse> call, @NonNull Throwable t) {
                //Display Error
            }
        });

    }

    private void showInRecyclerView() {

        recyclerView = findViewById(R.id.rv_movies);

        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }
}