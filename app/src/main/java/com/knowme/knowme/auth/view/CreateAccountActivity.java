package com.knowme.knowme.auth.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.knowme.knowme.R;
import com.knowme.knowme.auth.presenter.CreateAccountPresenter;
import com.knowme.knowme.model.User;
import com.knowme.knowme.util.Helper;

public class CreateAccountActivity extends AppCompatActivity implements ICreateAccountView{

    private static final String TAG = "Create Account Activity";
    private Toolbar toolbar;
    private TextView titleToolbar;
    private ImageView imageLeftToolbar;
    private ImageView imageRightToolbar;

    private TextInputEditText emailTextInputEditText, nameTextInputEditText, user_nameTextInputEditText,
            passwordTextInputEditText, confirm_passwordTextInputEditText;
    private Button joinUsButton;
    private ProgressBar mProgressBar;
    private View viewContainer;

    private CreateAccountPresenter createAccountPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        this.createAccountPresenter = new CreateAccountPresenter(this);
        //this.showToolbar(getResources().getString(R.string.text_create_account), true);
        this.setupToolbar();

        this.viewContainer = (View) findViewById(R.id.container);
        this.emailTextInputEditText = (TextInputEditText) findViewById(R.id.email);
        this.nameTextInputEditText = (TextInputEditText) findViewById(R.id.name);
        this.user_nameTextInputEditText = (TextInputEditText) findViewById(R.id.user_name);
        this.passwordTextInputEditText = (TextInputEditText) findViewById(R.id.password);
        this.confirm_passwordTextInputEditText = (TextInputEditText) findViewById(R.id.confirm_password);

        this.joinUsButton = (Button) findViewById(R.id.joinUsButton);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_create_account);
        this.toggleProgressBar(false);

        this.joinUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view.postDelayed()
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        createNewAccount();
                    }
                });
            }
        });
    }

    public void test() {
        Toast toast = Toast.makeText(this, "Los campos son requeridos.", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void createNewAccount() {

        String email = emailTextInputEditText.getText().toString();
        String name = nameTextInputEditText.getText().toString();
        String userName = user_nameTextInputEditText.getText().toString();
        String password = passwordTextInputEditText.getText().toString();
        String confirmPassword = confirm_passwordTextInputEditText.getText().toString();

        if(email.equals("") || name.equals("") || userName.equals("") || password.equals("")){
            Helper.showToast(this, "Los campos son requeridos." , Toast.LENGTH_LONG);
            return;
        }

        if(confirmPassword.equals("")){
            Helper.showToast(this, "Confirme su contraseña." , Toast.LENGTH_LONG);
            return;
        }

        if (!password.equals(confirmPassword)){
            Helper.showToast(this, "Las contraseñas deben coincidir." , Toast.LENGTH_LONG);
            return;
        }

        User user = new User(email, name, userName, password);
        createAccountPresenter.createAccount(user, CreateAccountActivity.this);
    }

    @Override
    public void createAcccountError(String error) {
        //Helper.showToast(this, error , Toast.LENGTH_LONG);
        Helper.showSnackBar(this.viewContainer, error, Snackbar.LENGTH_LONG,
                getResources().getColor(R.color.colorWhite),
                getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void toggleEnabledComponents(Boolean state) {
        this.emailTextInputEditText.setEnabled(state);
        this.nameTextInputEditText.setEnabled(state);
        this.user_nameTextInputEditText.setEnabled(state);
        this.passwordTextInputEditText.setEnabled(state);
        this.confirm_passwordTextInputEditText.setEnabled(state);
        this.joinUsButton.setEnabled(state);
    }

    @Override
    public void toggleProgressBar(Boolean state) {

        int stateVisibility = state == true ? View.VISIBLE : View.GONE;
        this.mProgressBar.setVisibility(stateVisibility);
    }

    @Override
    public void goToLogin(String message) {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
        onBackPressed();
        Helper.showToast(this, message, Toast.LENGTH_LONG);
    }

//    public void showToolbar(String title, Boolean showBackButton) {
//        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(this.toolbar);
//        getSupportActionBar().setTitle(title);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
//    }

    private void setupToolbar(){

        //this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.titleToolbar = (TextView) findViewById(R.id.title_toolbar);
        this.titleToolbar.setText(getResources().getString(R.string.text_create_account));

        this.imageLeftToolbar = (ImageView) findViewById(R.id.left_image_toolbar);
        //this.imageLeftToolbar.setImageResource(R.drawable.ic_action_logout);

        this.imageLeftToolbar.setBackgroundResource(R.drawable.ic_action_arrow_left);

        this.imageLeftToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.imageRightToolbar = (ImageView) findViewById(R.id.right_image_toolbar);
        this.imageRightToolbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
