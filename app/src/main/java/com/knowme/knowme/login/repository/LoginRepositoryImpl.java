package com.knowme.knowme.login.repository;

import android.graphics.Color;
import android.os.Handler;

import com.knowme.knowme.login.presenter.LoginPresenter;


/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginRepositoryImpl implements  LoginRepository {

    private LoginPresenter loginPresenter;

    public LoginRepositoryImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void signIn(String username, String password) {

        // code test
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean success =  true;
                if(success){
                    loginPresenter.signInSuccess();
                    return;
                }
                loginPresenter.signInError("Intente luego, ha ocurrido un error.");
            }
        }, 3000);
        // end code test
    }
}
