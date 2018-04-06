package com.knowme.knowme.finder.presenter;

import com.knowme.knowme.finder.repository.GitHubRepository;
import com.knowme.knowme.finder.repository.IGitHubRepository;
import com.knowme.knowme.finder.view.IUserGitHubView;
import com.knowme.knowme.model.UserGitHub;

import java.util.ArrayList;

/**
 * Created by coredeveloper on 4/5/18.
 */

public class UserGitHubPresenter implements IUserGitHubPresenter {

    private IUserGitHubView userGitHubView;
    private IGitHubRepository userRepository;

    public UserGitHubPresenter(IUserGitHubView userGitHubView){
        this.userGitHubView = userGitHubView;
        this.userRepository = new GitHubRepository(this);
    }

    @Override
    public void getUsers() {
        this.userRepository.getUsers();
    }

    @Override
    public void success(ArrayList<UserGitHub> data) {
        userGitHubView.setAdapter(data);
        userGitHubView.showRefreshView(false);
    }

    @Override
    public void error(String error) {
        userGitHubView.showRefreshView(false);
        userGitHubView.showMessageError(error);
    }
}
