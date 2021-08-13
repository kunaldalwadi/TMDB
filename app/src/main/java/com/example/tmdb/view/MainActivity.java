package com.example.tmdb.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.tmdb.R;
import com.example.tmdb.adapter.MovieAdapter;
import com.example.tmdb.databinding.MainActivityBinding;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MoviesServerResponse;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;
import com.example.tmdb.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    
//    private ArrayList<Movie> movies;
    private PagedList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainActivityViewModel mMainActivityViewModel;
    private MainActivityBinding mMainActivityBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        
        Objects.requireNonNull(getSupportActionBar()).setTitle("TMDB Popular Movies");
        
        getPopularMovies();
        
        mSwipeRefreshLayout = mMainActivityBinding.slSwipeLayout;
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }
    
    private void getPopularMovies() {
        
//        mMainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> moviesFromLiveData) {
//                movies = (ArrayList<Movie>) moviesFromLiveData;
//                showInRecyclerView();
//            }
//        });

        mMainActivityViewModel.getmMoviesPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> moviesFromLiveData) {
                movies = moviesFromLiveData;
                showInRecyclerView();
            }
        });

    }
    
    private void showInRecyclerView() {
        
        recyclerView = mMainActivityBinding.rvMovies;
        
        movieAdapter = new MovieAdapter(this);
        movieAdapter.submitList(movies);
        
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        
        mSwipeRefreshLayout.setRefreshing(false);
        
    }
}