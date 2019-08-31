package com.teguh.retrofitjava1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://www.thesportsdb.com/";

    @GET("api/v1/json/1/all_leagues.php")
    Call<League> getAllLeagues();

}
