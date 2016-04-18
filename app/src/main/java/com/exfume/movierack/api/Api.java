package com.exfume.movierack.api;

import com.exfume.movierack.model.Movie;
import com.exfume.movierack.model.MovieElement;
import com.exfume.movierack.model.ShowElement;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Benjides on 22/12/2015.
 */
public interface Api {

    int VERSION = 2;
    String CLIENT_ID = "4935507d3ff387756d9daee6053d86ce83b1d5838a58a8939aef055d13072933";

    /**
     * Movies Endpoints
     */

    @Headers({
            "Content-Type:application/json",
            "trakt-api-version:"+VERSION,
            "trakt-api-key:"+CLIENT_ID
    })
    @GET("/movies/{movie_id}?extended=full,images")
    Call<Movie> movieSummary(@Path("movie_id") String movie_id);


    @Headers({
            "Content-Type:application/json",
            "trakt-api-version:"+VERSION,
            "trakt-api-key:"+CLIENT_ID
    })
    @GET("/movies/trending?extended=images")
    Call<ArrayList<MovieElement>> trendingMovies(@Query("page") int page , @Query("limit") int limit);

    @Headers({
            "Content-Type:application/json",
            "trakt-api-version:"+VERSION,
            "trakt-api-key:"+CLIENT_ID
    })
    @GET("/search?type=movie")
    Call<ArrayList<MovieElement>> searchMovie(@Query("query")String query );


    /**
     * Shows Endpoints
     */
    @Headers({
            "Content-Type:application/json",
            "trakt-api-version:"+VERSION,
            "trakt-api-key:"+CLIENT_ID
    })
    @GET("/search?type=show")
    Call<ArrayList<ShowElement>> searchShow(@Query("query")String query );

    @Headers({
            "Content-Type:application/json",
            "trakt-api-version:"+VERSION,
            "trakt-api-key:"+CLIENT_ID
    })
    @GET("/shows/trending?extended=images")
    Call<ArrayList<ShowElement>> trendingShows(@Query("page") int page , @Query("limit") int limit);



}
