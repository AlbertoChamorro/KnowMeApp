package com.knowme.knowme.login.interactor;


import com.knowme.knowme.login.presenter.LoginPresenter;
import com.knowme.knowme.login.repository.LoginRepository;
import com.knowme.knowme.login.repository.LoginRepositoryImpl;

/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginInteractorImpl implements  LoginInteractor {

    private LoginPresenter loginPresenter;
    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginPresenter loginPresenter){
        this.loginPresenter = loginPresenter;
        this.loginRepository = new LoginRepositoryImpl(this.loginPresenter);
    }

    @Override
    public void signIn(String username, String password) {
        if(!username.equals("") && !password.equals("")) {
            this.loginRepository.signIn(username, password);
            return;
        }

        this.loginPresenter.signInError("Credenciales inválidas, verífique...");
    }
}
