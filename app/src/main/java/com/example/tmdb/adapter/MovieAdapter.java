package com.example.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.databinding.MovieListItemBinding;
import com.example.tmdb.model.Movie;
import com.example.tmdb.view.MovieDetailsActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;

//    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context){//, ArrayList<Movie> movieArrayList) {
        super(Movie.MOVIE_ITEM_CALLBACK);
        this.context = context;
//        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item, parent, false);
        
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    
//        Movie movie = movieArrayList.get(position);
        Movie movie = getItem(position);
        holder.mMovieListItemBinding.setMovie(movie);
    }

//    @Override
//    public int getItemCount() {
//        return movieArrayList.size();
//    }

    
    
    public class MovieViewHolder extends RecyclerView.ViewHolder{
        
        private MovieListItemBinding mMovieListItemBinding;
        
        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.mMovieListItemBinding = movieListItemBinding;
    
            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
        
                    int position = getAdapterPosition();
                    
                    if (position != RecyclerView.NO_POSITION)
                    {
//                        Movie selectedMovie = movieArrayList.get(position);
                        Movie selectedMovie = getItem(position);

                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });
            
        }
    }
}
