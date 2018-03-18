package com.knowme.knowme.auth.view;

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
import com.knowme.knowme.auth.presenter.ILoginPresenter;
import com.knowme.knowme.auth.presenter.LoginPresenter;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private TextView create_account_here;
    private Button loginButton;
    private ProgressBar progressBar;
    private ILoginPresenter loginPresenter;

    private TextView nameAppTextView;

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

        this.loginPresenter = new LoginPresenter(this);
        this.toogleProgressBar(false);
        this.loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                loginPresenter.signIn(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        this.nameAppTextView = (TextView) findViewById(R.id.name_app_textView);
        //R.color.colorPrimaryDark
       // this.nameAppTextView.setShadowLayer(30, 0, 0, Color.RED);
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
        Helper.showToast(this, error, Toast.LENGTH_LONG);
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
