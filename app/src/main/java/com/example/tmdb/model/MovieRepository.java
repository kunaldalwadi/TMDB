package com.example.tmdb.model;

import android.app.Application;
import android.util.Log;

import com.example.tmdb.R;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    
    private static final String TAG = MovieRepository.class.getSimpleName();
    
    private Application mApplication;
    private ArrayList<Movie> mMovieArrayList;
    private MutableLiveData<List<Movie>> mListMutableLiveData = new MutableLiveData<>();
    
    public MovieRepository(Application application) {
        mApplication = application;
    }
    
    public MutableLiveData<List<Movie>> getListMutableLiveData() {
    
        MovieDataService moviesData = RetrofitInstance.getRetrofit();
    
        Call<MoviesServerResponse> popularMovies = moviesData.getPopularMovies(mApplication.getApplicationContext().getString(R.string.api_key));
    
        popularMovies.enqueue(new Callback<MoviesServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesServerResponse> call, @NonNull Response<MoviesServerResponse> response) {
                //Success - Display Movies
                MoviesServerResponse body = response.body();
                if (response.code() == 200)
                {
                    if (body.getMovies() != null)
                    {
                        mMovieArrayList = (ArrayList<Movie>) body.getMovies();
                        
                        mListMutableLiveData.setValue(mMovieArrayList);
                    }
                }
                else
                {
                    Log.d(TAG, "onResponse: " + response.raw());
                }
            }
        
            @Override
            public void onFailure(@NonNull Call<MoviesServerResponse> call, @NonNull Throwable t) {
                //Display Error
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        
        return mListMutableLiveData;
    }
}
