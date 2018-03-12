package com.knowme.knowme.login.presenter;

import android.view.View;

/**
 * Created by coredeveloper on 3/11/18.
 */

public interface LoginPresenter {
    void signIn(String username, String password);
    void signInSuccess();
    void signInError(String error);
}
