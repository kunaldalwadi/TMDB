package com.example.tmdb.model;

import android.app.Application;

import com.example.tmdb.service.MovieDataService;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory{
    
    private MovieDataSource mMovieDataSource;
    private MovieDataService mMovieDataService;
    private Application mApplication;
    private MutableLiveData<MovieDataSource> mMovieDataSourceMutableLiveData;
    
    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        mMovieDataService = movieDataService;
        mApplication = application;
        
        mMovieDataSourceMutableLiveData = new MutableLiveData<>();
    }
    
    @Override
    public DataSource create() {
        
        mMovieDataSource = new MovieDataSource(mMovieDataService, mApplication);
        mMovieDataSourceMutableLiveData.postValue(mMovieDataSource);
        
        return mMovieDataSource;
    }
    
    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return mMovieDataSourceMutableLiveData;
    }
}
