package com.knowme.knowme.auth.presenter;

import android.app.Activity;

import com.knowme.knowme.model.User;

/**
 * Created by coredeveloper on 3/17/18.
 */

public interface ICreateAccountPresenter {
    void createAccount(User user, Activity activity);
    void createAccountSuccess(String message);
    void createAccountError(String error);
}
