package com.example.tmdb.viewmodel;

import android.app.Application;

import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {
    
    private MovieRepository mMovieRepository;
    
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        
        mMovieRepository = new MovieRepository(application);
    }
    
    public LiveData<List<Movie>> getAllMovies() {
        return mMovieRepository.getListMutableLiveData();
    }
}
