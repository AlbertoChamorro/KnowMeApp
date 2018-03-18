package com.knowme.knowme.auth.presenter;

import android.app.Activity;

import com.knowme.knowme.auth.interactor.CreateAccountInteractor;
import com.knowme.knowme.auth.interactor.ICreateAccountInteractor;
import com.knowme.knowme.auth.view.ICreateAccountView;
import com.knowme.knowme.model.User;

/**
 * Created by coredeveloper on 3/17/18.
 */

public class CreateAccountPresenter implements ICreateAccountPresenter {
    private ICreateAccountView createAccountView;
    private ICreateAccountInteractor createAccountInteractor;

    public CreateAccountPresenter(ICreateAccountView createAccountView){
        this.createAccountView = createAccountView;
        this.createAccountInteractor = new CreateAccountInteractor(this);
    }

    @Override
    public void createAccount(User user, Activity activity) {
        createAccountView.toogleEnabledComponents(false);
        createAccountView.toogleProgressBar(true);
        createAccountInteractor.createAccount(user, activity);
    }

    @Override
    public void createAccountSuccess(String message) {
        createAccountView.toogleEnabledComponents(true);
        createAccountView.toogleProgressBar(false);
        // go to login view
        createAccountView.goToLogin(message);
    }

    @Override
    public void createAccountError(String error) {
        createAccountView.toogleEnabledComponents(true);
        createAccountView.toogleProgressBar(false);
        createAccountView.createAcccountError(error);
    }
}
