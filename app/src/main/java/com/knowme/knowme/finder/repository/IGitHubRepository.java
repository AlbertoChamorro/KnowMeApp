package com.knowme.knowme.finder.repository;

import com.knowme.knowme.model.UserGitHub;

/**
 * Created by coredeveloper on 4/3/18.
 */

public interface IGitHubRepository {

    void getRepositories(String user);

    void getUsers();

    void getUser(String user);
}
