package com.knowme.knowme.auth.interactor;


import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.knowme.knowme.auth.presenter.ILoginPresenter;
import com.knowme.knowme.auth.repository.ILoginRepository;
import com.knowme.knowme.auth.repository.LoginRepository;

/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginInteractor implements ILoginInteractor {

    private ILoginPresenter loginPresenter;
    private ILoginRepository loginRepository;

    public LoginInteractor(ILoginPresenter loginPresenter){
        this.loginPresenter = loginPresenter;
        this.loginRepository = new LoginRepository(this.loginPresenter);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        if(!username.equals("") && !password.equals("")) {
            this.loginRepository.signIn(username, password, activity, firebaseAuth);
            return;
        }

        this.loginPresenter.signInError("Credenciales inválidas, verífique...");
    }
}
