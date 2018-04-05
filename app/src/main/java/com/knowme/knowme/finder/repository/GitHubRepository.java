package com.knowme.knowme.finder.repository;

import com.knowme.knowme.api.RetrofitManager;
import com.knowme.knowme.model.UserGitHub;

import java.util.List;

import retrofit2.Call;

/**
 * Created by coredeveloper on 4/3/18.
 */

public class GitHubRepository implements IGitHubRepository{

    private IGitHubRepository service;

    public GitHubRepository(){

        this.service = RetrofitManager.sharedInstance.retrofit.create(IGitHubRepository.class);
    }

    @Override
    public Call<List<UserGitHub>> getRepositories(String username) {
        return null;
    }

    @Override
    public Call<List<UserGitHub>> getUsers() {
        return null;
    }

    @Override
    public Call<UserGitHub> getUser(String user) {
        return null;
    }
}
