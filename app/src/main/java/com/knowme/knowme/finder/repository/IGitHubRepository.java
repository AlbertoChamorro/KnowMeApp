package com.knowme.knowme.finder.repository;

import com.knowme.knowme.model.UserGitHub;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.GET;

/**
 * Created by coredeveloper on 4/3/18.
 */

public interface IGitHubRepository {
    @GET("users/{user}/repos")
    Call<List<UserGitHub>> getRepositories(@Path("user") String user);
}
