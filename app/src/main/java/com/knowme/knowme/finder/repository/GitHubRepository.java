package com.knowme.knowme.finder.repository;

import com.knowme.knowme.api.RetrofitManager;
import com.knowme.knowme.finder.presenter.UserGitHubPresenter;
import com.knowme.knowme.model.UserGitHub;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by coredeveloper on 4/3/18.
 */

public class GitHubRepository implements IGitHubRepository {

    private IGitHubRepositoryApi service;
    private UserGitHubPresenter userGitHubPresenter;

    public GitHubRepository(UserGitHubPresenter userGitHubPresenter){

        this.userGitHubPresenter = userGitHubPresenter;
        this.service = RetrofitManager.sharedInstance.retrofit.create(IGitHubRepositoryApi.class);
    }

    @Override
    public void getRepositories(String user) {

    }

    @Override
    public void getUsers() {

        Call<List<UserGitHub>> call = service.getUsers();
        call.enqueue(new Callback<List<UserGitHub>>() {
            @Override
            public void onResponse(Call<List<UserGitHub>> call, Response<List<UserGitHub>> response) {
                switch (response.code()){
                    case 200:
                        List<UserGitHub> data = response.body();
                        userGitHubPresenter.success((ArrayList<UserGitHub>) data);
                        break;
                    case 401: break;
                    default: break;
                }
            }

            @Override
            public void onFailure(Call<List<UserGitHub>> call, Throwable t) {
                userGitHubPresenter.error(t.toString());
            }
        });
    }

    @Override
    public void getUser(String user) {

    }
}
