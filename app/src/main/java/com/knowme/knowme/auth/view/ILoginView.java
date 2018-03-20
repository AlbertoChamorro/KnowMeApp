package com.knowme.knowme.auth.view;

import android.view.View;

/**
 * Created by coredeveloper on 3/11/18.
 */
public interface ILoginView {

    void login();

    void createNewAccount(View view);

    void goHome();

    void redirectToWebPage(View view);

    void loginError(String error);

    void toggleEnabledComponents(Boolean state);

    void toggleProgressBar(Boolean state);
}
