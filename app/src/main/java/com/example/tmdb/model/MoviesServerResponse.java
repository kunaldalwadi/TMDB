package com.example.tmdb.model;

import android.os.Parcelable;

import com.example.tmdb.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MoviesServerResponse extends BaseObservable implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;
    
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;
    
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    
    
    public final static Creator<MoviesServerResponse> CREATOR = new Creator<MoviesServerResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MoviesServerResponse createFromParcel(android.os.Parcel in) {
            return new MoviesServerResponse(in);
        }

        public MoviesServerResponse[] newArray(int size) {
            return (new MoviesServerResponse[size]);
        }

    };

    protected MoviesServerResponse(android.os.Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movies, (com.example.tmdb.model.Movie.class.getClassLoader()));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MoviesServerResponse() {
    }

    @Bindable
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        notifyPropertyChanged(BR.page);
    }
    
    @Bindable
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> results) {
        this.movies = results;
        notifyPropertyChanged(BR.movies);
    }
    
    @Bindable
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        notifyPropertyChanged(BR.totalPages);
    }
    
    @Bindable
    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        notifyPropertyChanged(BR.totalResults);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(movies);
        dest.writeValue(totalPages);
        dest.writeValue(totalResults);
    }

    public int describeContents() {
        return 0;
    }

}
