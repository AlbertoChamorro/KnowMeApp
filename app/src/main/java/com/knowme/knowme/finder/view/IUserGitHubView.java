package com.knowme.knowme.finder.view;

import com.knowme.knowme.model.UserGitHub;

import java.util.ArrayList;

/**
 * Created by coredeveloper on 4/5/18.
 */

public interface IUserGitHubView {

    void loadData();
    void setAdapter(ArrayList<UserGitHub> data);
    void showRefreshView(Boolean state);
    void showMessageError(String error);
}
