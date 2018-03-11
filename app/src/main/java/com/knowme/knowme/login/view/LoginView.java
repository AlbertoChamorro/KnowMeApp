package com.knowme.knowme.login.view;

import android.view.View;

/**
 * Created by coredeveloper on 3/11/18.
 */

public interface LoginView {

    void createNewAccount(View view);

    void login(View view);

    void redirectToWebPage(View view);

    void loginError(String error);

    void toogleEnabledComponents(Boolean state);

    void toogleProgressBar(Boolean state);
}
