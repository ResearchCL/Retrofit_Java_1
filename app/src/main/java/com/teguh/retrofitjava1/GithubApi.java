package com.teguh.retrofitjava1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Repo>> getRepositoriesFromUser(@Path("user") String user);

}
