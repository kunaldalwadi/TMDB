package com.example.tmdb.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.tmdb.R;
import com.example.tmdb.model.MoviesServerResponse;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

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

                if (response.isSuccessful())
                {
                    MoviesServerResponse body = response.body();

                    Log.d(TAG, "onResponse: ");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesServerResponse> call, @NonNull Throwable t) {
                //Display Error
            }
        });

    }
}