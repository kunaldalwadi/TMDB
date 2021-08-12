package com.example.tmdb.model;

import android.app.Application;

import com.example.tmdb.R;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    
    private MovieDataService mMovieDataService;
    private Application mApplication;
    
    public MovieDataSource(MovieDataService movieDataService, Application application) {
        mMovieDataService = movieDataService;
        mApplication = application;
    }
    
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        //Initially loads the data for the first time
        Call<MoviesServerResponse> popularMoviesWithPaging = RetrofitInstance.getRetrofit().getPopularMoviesWithPaging(String.valueOf(R.string.api_key), 1);
        
        popularMoviesWithPaging.enqueue(new Callback<MoviesServerResponse>() {
            @Override
            public void onResponse(Call<MoviesServerResponse> call, Response<MoviesServerResponse> response) {
    
                if (response.body() != null && response.body().getMovies() != null)
                {
                    MoviesServerResponse body = response.body();
                    List<Movie> movies = new ArrayList<>();
                    movies = body.getMovies();
                    
                    callback.onResult(movies,null, 2);
                }
            }
    
            @Override
            public void onFailure(Call<MoviesServerResponse> call, Throwable t) {
        
            }
        });
    }
    
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
    
    }
    
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        //Loads data everytime after the first load.
    
        Call<MoviesServerResponse> popularMoviesWithPaging = RetrofitInstance.getRetrofit().getPopularMoviesWithPaging(String.valueOf(R.string.api_key), params.key);
    
        popularMoviesWithPaging.enqueue(new Callback<MoviesServerResponse>() {
            @Override
            public void onResponse(Call<MoviesServerResponse> call, Response<MoviesServerResponse> response) {
            
                if (response.body() != null && response.body().getMovies() != null)
                {
                    MoviesServerResponse body = response.body();
                    List<Movie> movies = new ArrayList<>();
                    movies = body.getMovies();
                
                    callback.onResult(movies, params.key+1);
                }
            }
        
            @Override
            public void onFailure(Call<MoviesServerResponse> call, Throwable t) {
            
            }
        });
        
    }
}
