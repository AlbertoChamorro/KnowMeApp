package com.knowme.knowme.auth.interactor;


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
    public void signIn(String username, String password) {
        if(!username.equals("") && !password.equals("")) {
            this.loginRepository.signIn(username, password);
            return;
        }

        this.loginPresenter.signInError("Credenciales inválidas, verífique...");
    }
}
