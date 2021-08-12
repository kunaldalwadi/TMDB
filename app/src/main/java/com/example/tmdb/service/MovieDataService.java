package com.example.tmdb.service;

import com.example.tmdb.model.MoviesServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

//https://api.themoviedb.org/3/movie/popular?api_key=b133c1180758351bf9a6631f15a7457f&language=en-US&page=1
    @GET("3/movie/popular")
    Call<MoviesServerResponse> getPopularMovies(@Query("api_key")String apiKey);
    
    
    @GET("3/movie/popular")
    Call<MoviesServerResponse> getPopularMoviesWithPaging(@Query("api_key")String apiKey, @Query("page")int page);

}
