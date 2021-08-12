package com.example.tmdb.viewmodel;

import android.app.Application;

import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieDataSource;
import com.example.tmdb.model.MovieDataSourceFactory;
import com.example.tmdb.model.MovieRepository;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    
    private MovieRepository mMovieRepository;
    private MovieDataSourceFactory mMovieDataSourceFactory;
    private MovieDataService mMovieDataService;
    private LiveData<MovieDataSource> mMovieDataSourceLiveData;
    
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        
        mMovieRepository = new MovieRepository(application);
        mMovieDataService = RetrofitInstance.getRetrofit();
        
        mMovieDataSourceFactory = new MovieDataSourceFactory(mMovieDataService, application);
    
        mMovieDataSourceLiveData = mMovieDataSourceFactory.getMovieDataSourceMutableLiveData();
    
    }
    
    public LiveData<List<Movie>> getAllMovies() {
        return mMovieRepository.getListMutableLiveData();
    }
}
