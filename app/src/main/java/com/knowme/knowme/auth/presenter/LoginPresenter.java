package com.knowme.knowme.auth.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.knowme.knowme.auth.interactor.ILoginInteractor;
import com.knowme.knowme.auth.interactor.LoginInteractor;
import com.knowme.knowme.auth.view.ILoginView;

/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor(this);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        loginView.toogleEnabledComponents(false);
        loginView.toogleProgressBar(true);
        loginInteractor.signIn(username, password, activity, firebaseAuth);
    }

    @Override
    public void signInSuccess() {
        loginView.toogleEnabledComponents(true);
        loginView.toogleProgressBar(false);
        loginView.goHome();
    }

    @Override
    public void signInError(String error) {
        loginView.toogleEnabledComponents(true);
        loginView.toogleProgressBar(false);
        loginView.loginError(error);
    }
}
