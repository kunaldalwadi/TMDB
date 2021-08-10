package com.example.tmdb.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.databinding.MovieDetailsActivityBinding;
import com.example.tmdb.model.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ui.AppBarConfiguration;

public class MovieDetailsActivity extends AppCompatActivity {
    
    private AppBarConfiguration appBarConfiguration;
    private MovieDetailsActivityBinding mMovieDetailsActivityBinding;
    
    private Movie mMovie;
   
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieDetailsActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_details);

        setSupportActionBar(mMovieDetailsActivityBinding.toolbar);
        
        Intent intent = getIntent();
        
        if (intent.hasExtra("movie"))
        {
            mMovie = intent.getParcelableExtra("movie");
    
            String path = "https://image.tmdb.org/t/p/w500/" + mMovie.getPosterPath();
    
            Glide.with(this)
                 .load(path)
                 .placeholder(R.mipmap.ic_launcher_round)
                 .into(mMovieDetailsActivityBinding.ivMovieLargeIcon);
            
            getSupportActionBar().setTitle(mMovie.getTitle());
            
            Toast.makeText(this, mMovie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
        }
    
    }
}