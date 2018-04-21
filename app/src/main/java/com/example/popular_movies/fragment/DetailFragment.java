package com.example.popular_movies.fragment;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popular_movies.R;
import com.example.popular_movies.adapter.MovieReviewsAdapter;
import com.example.popular_movies.model.Movie;
import com.example.popular_movies.provider.MoviesContract;
import com.example.popular_movies.service.MoviesRetrofit;
import com.squareup.picasso.Picasso;

/**
 * Created by Alex on 12/03/2018.
 */

public class DetailFragment extends Fragment{

    public static MovieReviewsAdapter movieReviewsAdapter;
    private RecyclerView getmRecyclerView_review;
    private MoviesRetrofit mMoviesDetails;
    private int mId;
    private String mTitle,mPoster,mDescription,mVote,mRelease,mBack;
    private TextView mMovieTitle, mMovieDescription,mVoteAverage,mReleaseDate;
    private ImageView mBackPoster;
    private ContentResolver resolver;
    private ContentValues values;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        getmRecyclerView_review = view.findViewById(R.id.review_recycler);
        getmRecyclerView_review.setLayoutManager(new LinearLayoutManager(getActivity()));

        mMoviesDetails = new MoviesRetrofit(getActivity());

        if(movieReviewsAdapter == null) {
            movieReviewsAdapter = new MovieReviewsAdapter(getActivity());
        }

        getmRecyclerView_review.setAdapter(movieReviewsAdapter);

        mMovieTitle = view.findViewById(R.id.movie_title);
        mMovieDescription = view.findViewById(R.id.movie_description);
        mVoteAverage = view.findViewById(R.id.vote_average);
        mReleaseDate = view.findViewById(R.id.release_date);
        mBackPoster = view.findViewById(R.id.back_detail);

        resolver = getActivity().getContentResolver();
        values = new ContentValues();


        if(getArguments() != null) {

            mId = getArguments().getInt("id");
            mTitle = getArguments().getString("title");
            mDescription = getArguments().getString("description");
            mVote = getArguments().getString("vote_average");
            mRelease = getArguments().getString("release_date");
            mBack = getArguments().getString("backdrop");
            mPoster = getArguments().getString("poster");

            values.put(MoviesContract.Movie.MOVIE_ID, mId);
            values.put(MoviesContract.Movie.TITLE, mTitle);
            values.put(MoviesContract.Movie.POSTER, mPoster);
            values.put(MoviesContract.Movie.DESCRIPTION, mDescription);
            values.put(MoviesContract.Movie.VOTE_AVERAGE, mVote);
            values.put(MoviesContract.Movie.RELEASE_DATE, mRelease);
            values.put(MoviesContract.Movie.BACKDROP, mBack);

            mMoviesDetails.retroTrailers(mId);
            mMoviesDetails.retroReviews(mId);

            mMovieTitle.setText(mTitle);
            mMovieDescription.setText(mDescription);
            mVoteAverage.setText(mVote);
            mReleaseDate.setText(mRelease);

            Picasso.with(getActivity())
                    .load(Movie.getTmdbBackDropPath() + mBack)
                    .into(mBackPoster);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    public void setFragmentData(Movie movie){

            mId = movie.getId();
            mTitle = movie.getTitle();
            mDescription = movie.getDescription();
            mVote = movie.getVote_average();
            mRelease = movie.getRelease_date();
            mBack = movie.getBackdrop();
            mPoster = movie.getPoster();

        mMovieTitle.setText(mTitle);
        mMovieDescription.setText(mDescription);
        mVoteAverage.setText(mVote);
        mReleaseDate.setText(mRelease);

        Picasso.with(getActivity())
                .load(Movie.getTmdbBackDropPath() + mBack)
                .into(mBackPoster);

        values.clear();
        values.put(MoviesContract.Movie.MOVIE_ID, mId);
        values.put(MoviesContract.Movie.TITLE, mTitle);
        values.put(MoviesContract.Movie.POSTER, mPoster);
        values.put(MoviesContract.Movie.DESCRIPTION, mDescription);
        values.put(MoviesContract.Movie.VOTE_AVERAGE, mVote);
        values.put(MoviesContract.Movie.RELEASE_DATE, mRelease);
        values.put(MoviesContract.Movie.BACKDROP, mBack);


        movieReviewsAdapter = new MovieReviewsAdapter(getActivity());
        getmRecyclerView_review.setAdapter(movieReviewsAdapter);

        mMoviesDetails.retroTrailers(mId);
        mMoviesDetails.retroReviews(mId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
