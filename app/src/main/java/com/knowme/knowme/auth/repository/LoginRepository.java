package com.knowme.knowme.auth.repository;

import android.os.Handler;

import com.knowme.knowme.auth.presenter.ILoginPresenter;


/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginRepository implements ILoginRepository {

    private ILoginPresenter loginPresenter;

    public LoginRepository(ILoginPresenter loginPresenter) {
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
