package com.knowme.knowme.auth.presenter;

/**
 * Created by coredeveloper on 3/11/18.
 */

public interface ILoginPresenter {
    void signIn(String username, String password);
    void signInSuccess();
    void signInError(String error);
}
