package com.exfume.movierack.api;

import com.exfume.movierack.adapter.MovieAdapter;
import com.exfume.movierack.adapter.ShowAdapter;
import com.exfume.movierack.model.Movie;
import com.exfume.movierack.model.MovieElement;
import com.exfume.movierack.model.ShowElement;


import java.util.ArrayList;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Benjides on 22/12/2015.
 */
public class TraktRest {


    private static String URL = "https://api-v2launch.trakt.tv";
    private Retrofit retrofit;
    private Api apiService;

    public TraktRest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(Api.class);
    }

    //EndPoints
    public void trendingMovies(MovieAdapter adapter) {
        Call<ArrayList<MovieElement>> Call = apiService.trendingMovies(1, 10);
        Call.enqueue(adapter);
    }


    public void trendingMovies(int page, int limit, MovieAdapter adapter) {
        Call<ArrayList<MovieElement>> Call = apiService.trendingMovies(page, limit);
        Call.enqueue(adapter);
    }

    public void searchMovies(String query, MovieAdapter adapter) {
        Call<ArrayList<MovieElement>> Call = apiService.searchMovie(query);
        Call.enqueue(adapter);
    }



    public void trendingShows(ShowAdapter adapter) {
        Call<ArrayList<ShowElement>> Call = apiService.trendingShows(1 , 10);
        Call.enqueue(adapter);
    }
    public void trendingShows(int page, int limit, ShowAdapter adapter) {
        Call<ArrayList<ShowElement>> Call = apiService.trendingShows(page, limit);
        Call.enqueue(adapter);
    }


    public void searchShows(String query, ShowAdapter adapter) {
        Call<ArrayList<ShowElement>> Call = apiService.searchShow(query);
        Call.enqueue(adapter);
    }

}