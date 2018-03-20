package com.knowme.knowme.auth.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.knowme.knowme.R;
import com.knowme.knowme.auth.presenter.ILoginPresenter;
import com.knowme.knowme.auth.presenter.LoginPresenter;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private static final String TAG = "Login Activity";
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private TextView create_account_here;
    private Button loginButton;
    private ProgressBar progressBar;
    private ILoginPresenter loginPresenter;
    private TextView nameAppTextView;
    private View viewContainer;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.viewContainer = (View) findViewById(R.id.container);
        this.usernameEditText = (TextInputEditText) findViewById(R.id.user_name);
        this.passwordEditText = (TextInputEditText) findViewById(R.id.password);
        this.loginButton = (Button) findViewById(R.id.login_button);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar_login);
        this.create_account_here = (TextView) findViewById(R.id.create_account_here);

        this.create_account_here.setText(Helper.underlineText(this.create_account_here.getText().toString()));

        this.loginPresenter = new LoginPresenter(this);
        this.toggleProgressBar(false);
        this.loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                login();
            }
        });

        this.nameAppTextView = (TextView) findViewById(R.id.name_app_textView);
        //R.color.colorPrimaryDark
       // this.nameAppTextView.setShadowLayer(30, 0, 0, Color.RED);

        // init var config firebase
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG, "User Logged - " + firebaseUser.getEmail());
                }else{
                    Log.w(TAG, "User Not Logged.");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginPresenter.signIn(username, password, this, firebaseAuth);
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
        //Helper.showToast(this, error, Toast.LENGTH_LONG);
        Helper.showSnackBar(this.viewContainer, error, Snackbar.LENGTH_LONG,
                            getResources().getColor(R.color.colorWhite),
                            getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void toggleEnabledComponents(Boolean state) {
        this.usernameEditText.setEnabled(state);
        this.passwordEditText.setEnabled(state);
        this.loginButton.setEnabled(state);
    }

    @Override
    public void toggleProgressBar(Boolean state) {

        int stateVisibility = state == true ? View.VISIBLE : View.GONE;
        this.progressBar.setVisibility(stateVisibility);
    }
}
