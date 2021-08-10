package com.example.tmdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;

    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movieArrayList.get(position).getTitle());
        holder.movieRating.setText(Integer.toString(movieArrayList.get(position).getVoteCount()));

        String path = "https://image.tmdb.org/t/p/w500/" + movieArrayList.get(position).getPosterPath();

        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        public TextView movieTitle, movieRating;
        public ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.iv_movie);
            movieTitle = itemView.findViewById(R.id.tv_movie_title);
            movieRating = itemView.findViewById(R.id.tv_movie_rating);
        }
    }
}
