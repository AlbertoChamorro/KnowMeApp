package com.knowme.knowme.auth.interactor;

import android.app.Activity;

import com.knowme.knowme.model.User;

/**
 * Created by coredeveloper on 3/17/18.
 */

public interface ICreateAccountInteractor {

    void createAccount(User user, Activity activity);
}
