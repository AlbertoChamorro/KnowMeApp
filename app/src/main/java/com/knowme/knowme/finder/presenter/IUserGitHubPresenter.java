package com.knowme.knowme.finder.presenter;

import com.knowme.knowme.model.UserGitHub;

import java.util.ArrayList;

/**
 * Created by coredeveloper on 4/5/18.
 */

public interface IUserGitHubPresenter {

    void getUsers();
    void success(ArrayList<UserGitHub> data);
    void error(String error);
}
