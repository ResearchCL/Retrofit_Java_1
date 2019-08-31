package com.teguh.retrofitjava1.api;

import com.teguh.retrofitjava1.model.Team;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SportDbApi {

    String BASE_URL = "https://www.thesportsdb.com/";

    // EPL => 4328
    @GET("api/v1/json/1/lookup_all_teams.php")
    Call<Team> getAllTeams(@Query("id") String idTeam);

}
