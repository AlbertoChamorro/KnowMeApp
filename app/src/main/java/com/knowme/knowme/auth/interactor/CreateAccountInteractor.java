package com.knowme.knowme.auth.interactor;

import android.app.Activity;

import com.knowme.knowme.auth.presenter.CreateAccountPresenter;
import com.knowme.knowme.auth.repository.CreateAccountRepository;
import com.knowme.knowme.model.User;

/**
 * Created by coredeveloper on 3/17/18.
 */

public class CreateAccountInteractor implements ICreateAccountInteractor {

    private CreateAccountPresenter createAccountPresenter;
    private CreateAccountRepository createAccountRepository;

    public CreateAccountInteractor(CreateAccountPresenter createAccountPresenter){
        this.createAccountPresenter = createAccountPresenter;
        this.createAccountRepository = new CreateAccountRepository(this.createAccountPresenter);
    }

    @Override
    public void createAccount(User user, Activity activity) {
        createAccountRepository.createAccount(user, activity);
    }
}
