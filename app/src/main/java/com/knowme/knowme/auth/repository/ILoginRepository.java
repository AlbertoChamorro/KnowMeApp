package com.knowme.knowme.auth.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by coredeveloper on 3/11/18.
 */

public interface ILoginRepository {
    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
