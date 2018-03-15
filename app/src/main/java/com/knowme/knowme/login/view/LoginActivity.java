package com.knowme.knowme.login.view;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.knowme.knowme.R;
import com.knowme.knowme.login.presenter.LoginPresenter;
import com.knowme.knowme.login.presenter.LoginPresenterImpl;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.view.CreateAccountActivity;
import com.knowme.knowme.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private TextView create_account_here;
    private Button loginButton;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.usernameEditText = (TextInputEditText) findViewById(R.id.user_name);
        this.passwordEditText = (TextInputEditText) findViewById(R.id.password);
        this.loginButton = (Button) findViewById(R.id.login_button);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar_login);
        this.create_account_here = (TextView) findViewById(R.id.create_account_here);

        this.create_account_here.setText(Helper.underlineText(this.create_account_here.getText().toString()));

        this.loginPresenter = new LoginPresenterImpl(this);
        this.toogleProgressBar(false);
        this.loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                loginPresenter.signIn(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    @Override
    public void createNewAccount(View view) {
        Intent createAccountActivity = new Intent(this, CreateAccountActivity.class);
        startActivity(createAccountActivity);
    }

    @Override
    public void goHome() {
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    @Override
    public void redirectToWebPage(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.marca.com"));
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toogleEnabledComponents(Boolean state) {
        this.usernameEditText.setEnabled(state);
        this.passwordEditText.setEnabled(state);
        this.loginButton.setEnabled(state);
    }

    @Override
    public void toogleProgressBar(Boolean state) {

        int stateVisibility = state == true ? View.VISIBLE : View.GONE;
        this.progressBar.setVisibility(stateVisibility);
    }
}
