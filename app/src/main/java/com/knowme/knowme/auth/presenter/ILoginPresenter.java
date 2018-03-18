package com.knowme.knowme.auth.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by coredeveloper on 3/11/18.
 */

public interface ILoginPresenter {
    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
    void signInSuccess();
    void signInError(String error);
}
