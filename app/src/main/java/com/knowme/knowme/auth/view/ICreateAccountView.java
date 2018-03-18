package com.knowme.knowme.auth.view;

import android.view.View;

/**
 * Created by coredeveloper on 3/17/18.
 */

public interface ICreateAccountView {

    void createNewAccount();

    void createAcccountError(String error);

    void toogleEnabledComponents(Boolean state);

    void toogleProgressBar(Boolean state);

    void goToLogin(String message);
}
