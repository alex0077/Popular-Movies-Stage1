package com.example.popular_movies.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popular_movies.MovieListActivity;
import com.example.popular_movies.R;
import com.example.popular_movies.adapter.MoviesAdapter;
import com.example.popular_movies.service.MoviesRetrofit;

/**
 * Created by Alex on 12/03/2018.
 */
public class ActivityFragment extends Fragment{

    private RecyclerView mRecyclerView;
    public static MoviesAdapter mAdapter;
    private Parcelable mListState;
    private String query;
    private String movieLang;
    private static final String LIST_STATE_KEY = "ListRestore";
    private static final String MOVIE_QUERY = "MovieQuery";
    private static final String LANG_MOVIE = "movieLang";
    private MoviesRetrofit mMoviesRetro;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.activity_movie_list_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        if(MovieListActivity.getDual()) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }

        if(mAdapter == null) {
            mAdapter = new MoviesAdapter(getActivity());
        }

        mRecyclerView.setAdapter(mAdapter);
        mMoviesRetro = new MoviesRetrofit(getActivity());

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        mListState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        savedInstanceState.putParcelable(LIST_STATE_KEY,mListState);
        savedInstanceState.putString(MOVIE_QUERY,getQuery());
        savedInstanceState.putString(LANG_MOVIE, movieLang);
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String prefLang = sharedPrefs.getString(getString(R.string.pref_language_key), getString(R.string.pref_value_lang_en));

        if (!movieLang.equals(prefLang)) {

            movieLang = prefLang;
            mMoviesRetro.retroMovies(getQuery(), movieLang);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (savedInstanceState == null) {

            setQuery(getResources().getString(R.string.pref_value_popular));
            movieLang = sharedPrefs.getString(getString(R.string.pref_language_key), getString(R.string.pref_value_lang_en));
            mMoviesRetro.retroMovies(getQuery(), movieLang);
        } else {

            String savedQuery = savedInstanceState.getString(MOVIE_QUERY);
            String savedLang = savedInstanceState.getString(LANG_MOVIE);

                setQuery(savedQuery);
                movieLang = savedLang;
                mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
                mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState);
            mMoviesRetro.retroMovies(getQuery(), movieLang);
            }
        }

    private void setQuery(String query){
        this.query = query;
    }

    private String getQuery(){
        return query;
    }

    public void refreshMenuMovies(String q){
        setQuery(q);
        mMoviesRetro.retroMovies(q, movieLang);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
