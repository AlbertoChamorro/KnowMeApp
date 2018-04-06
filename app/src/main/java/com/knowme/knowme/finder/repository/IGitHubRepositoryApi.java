package com.knowme.knowme.finder.repository;

import com.knowme.knowme.model.UserGitHub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by coredeveloper on 4/6/18.
 */

public interface IGitHubRepositoryApi {

    @GET("users/{user}/repos")
    Call<List<UserGitHub>> getRepositories(@Path("user") String user);

    @GET("users")
    Call<List<UserGitHub>> getUsers();

    @GET("users/{user}")
    Call<UserGitHub> getUser(@Path("user") String user);
}
