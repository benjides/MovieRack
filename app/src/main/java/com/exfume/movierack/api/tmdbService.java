package com.exfume.movierack.api;


import com.exfume.movierack.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;



public interface tmdbService {

    int VERSION = 3;
    String API_KEY = "b3162b892dc842005579761440afb2c4";


    @GET("/"+VERSION+"/movie/popular?api_key="+API_KEY+"&language=en-US")
    Call<Result> popularMovies();

}
