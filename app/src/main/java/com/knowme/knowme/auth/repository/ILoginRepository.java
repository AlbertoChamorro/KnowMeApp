package com.knowme.knowme.auth.repository;

/**
 * Created by coredeveloper on 3/11/18.
 */

public interface ILoginRepository {
    void signIn(String username, String password);
}
