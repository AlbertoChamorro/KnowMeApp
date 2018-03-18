package com.knowme.knowme.auth.repository;

import android.app.Activity;

import com.knowme.knowme.model.User;

/**
 * Created by coredeveloper on 3/17/18.
 */

public interface ICreateAccountRepository {

    void createAccount(User user, Activity activity);
}
