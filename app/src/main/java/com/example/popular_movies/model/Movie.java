package com.example.popular_movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alex on 12/03/2018.
 */
public class Movie {

    private static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    private static final String TMDB_BACK_DROP_PATH = "http://image.tmdb.org/t/p/w1280";
    private static final String TMDB_ENDPOINT = "http://api.themoviedb.org/3";

    @SerializedName("original_title")
    private String title;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    private String vote_average;

    private String release_date;

    private int id;

    public Movie() {
    }

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie(int id, String title, String poster, String description,
                 String vote_average, String release_date, String backdrop, int is_favorite) {

        this.id = id;
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.backdrop = backdrop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public static String getTmdbBackDropPath() {
        return TMDB_BACK_DROP_PATH;
    }

    public static String getTmdbEndpoint() {
        return TMDB_ENDPOINT;
    }

    public static String getTmdbImagePath() {
        return TMDB_IMAGE_PATH;
    }

    public static class MovieData {
        private List<Movie> results;

        public List<Movie> getData() {
            return results;
        }
    }
}
