package com.exfume.movierack.api;


import com.exfume.movierack.adapter.MovieAdapter;
import com.exfume.movierack.model.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tmdbRest {

    private static String URL = "https://api.themoviedb.org";
    private Retrofit retrofit;
    private tmdbService apiService;

    public tmdbRest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(tmdbService.class);
    }

    //EndPoints
    public void popularMovies(MovieAdapter adapter) {
        Call<Result> Call = apiService.popularMovies();
        Call.enqueue(adapter);
    }

}
