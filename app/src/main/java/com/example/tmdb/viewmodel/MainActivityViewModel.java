package com.example.tmdb.viewmodel;

import android.app.Application;

import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieDataSource;
import com.example.tmdb.model.MovieDataSourceFactory;
import com.example.tmdb.model.MovieRepository;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MainActivityViewModel extends AndroidViewModel {
    
    private MovieRepository mMovieRepository;
    private MovieDataSourceFactory mMovieDataSourceFactory;
    private MovieDataService mMovieDataService;
    private LiveData<MovieDataSource> mMovieDataSourceLiveData;

    private Executor mExecutor;
    private LiveData<PagedList<Movie>> mMoviesPagedList;
    
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        
        mMovieRepository = new MovieRepository(application);
        mMovieDataService = RetrofitInstance.getRetrofit();
        
        mMovieDataSourceFactory = new MovieDataSourceFactory(mMovieDataService, application);
    
        mMovieDataSourceLiveData = mMovieDataSourceFactory.getMovieDataSourceMutableLiveData();


        PagedList.Config mConfig = (new PagedList.Config.Builder())
                                    .setEnablePlaceholders(true)
                                    .setInitialLoadSizeHint(10)
                                    .setPageSize(20)
                                    .setPrefetchDistance(4)
                                    .build();

        mExecutor = Executors.newFixedThreadPool(5);

        mMoviesPagedList = (new LivePagedListBuilder<Integer, Movie>( mMovieDataSourceFactory, mConfig))
                            .setFetchExecutor(mExecutor)
                            .build();

    
    }
    
    public LiveData<List<Movie>> getAllMovies() {
        return mMovieRepository.getListMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getmMoviesPagedList() {
        return mMoviesPagedList;
    }
}
