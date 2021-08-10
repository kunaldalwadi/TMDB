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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MovieDetailsActivity extends AppCompatActivity {
    
    private AppBarConfiguration appBarConfiguration;
    private MovieDetailsActivityBinding binding;
    
    private Movie mMovie;
    private ImageView mImageView;
    private TextView mTitle, mRating, mSynopsis, mReleaseDate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = MovieDetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        
//        NavController navController = Navigation.findNavController(this, R.id.ll_content_movie_details);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        
        mTitle = findViewById(R.id.tv_movie_title);
        mRating = findViewById(R.id.tv_movie_rating);
        mSynopsis = findViewById(R.id.tv_movie_synopsis);
        mReleaseDate = findViewById(R.id.tv_movie_release_date);
    
        Intent intent = getIntent();
        
        if (intent.hasExtra("movie"))
        {
            mMovie = intent.getParcelableExtra("movie");
    
            String path = "https://image.tmdb.org/t/p/w500/" + mMovie.getPosterPath();
    
            Glide.with(this)
                 .load(path)
                 .placeholder(R.mipmap.ic_launcher_round)
                 .into(binding.ivMovieLargeIcon);
            
            getSupportActionBar().setTitle(mMovie.getTitle());
            
            mTitle.setText(mMovie.getTitle());
            mRating.setText(Double.toString(mMovie.getVoteAverage()));
            mSynopsis.setText(mMovie.getOverview());
            mReleaseDate.setText(mMovie.getReleaseDate());
            
            Toast.makeText(this, mMovie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
        }
    
    }
    
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.ll_content_movie_details);
//        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
//    }
}