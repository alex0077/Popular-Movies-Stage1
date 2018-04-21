package com.example.popular_movies.service;

import com.example.popular_movies.model.Movie;
import com.example.popular_movies.model.Review;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Alex on 19/03/2018.
 */

interface MoviesDataSource {
   @GET("/movie/popular")
    void getPopularMOvies(Callback<Movie.MovieData> cback);

    @GET("/movie/top_rated")
    void getTopMovies(Callback<Movie.MovieData> cback);


    @GET("/reviews")
    void getReviews(Callback<Review.ReviewResult> cback);
}

