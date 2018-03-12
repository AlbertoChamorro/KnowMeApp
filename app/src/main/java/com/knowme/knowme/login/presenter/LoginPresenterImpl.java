package com.knowme.knowme.login.presenter;

import com.knowme.knowme.login.interactor.LoginInteractor;
import com.knowme.knowme.login.interactor.LoginInteractorImpl;
import com.knowme.knowme.login.view.LoginView;

/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginPresenterImpl implements  LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginView.toogleEnabledComponents(false);
        loginView.toogleProgressBar(true);
        loginInteractor.signIn(username, password);
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
