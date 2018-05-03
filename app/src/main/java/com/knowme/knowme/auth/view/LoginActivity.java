package com.knowme.knowme.auth.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.knowme.knowme.R;
import com.knowme.knowme.auth.presenter.ILoginPresenter;
import com.knowme.knowme.auth.presenter.LoginPresenter;
import com.knowme.knowme.database.DatabaseHelper;
import com.knowme.knowme.model.ManagmentDatabase;
import com.knowme.knowme.model.User;
import com.knowme.knowme.util.Helper;
import com.knowme.knowme.view.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.security.AccessController.getContext;

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

    // vars setup sdk facebook
    private CallbackManager callbackManager;
    private LoginButton loginFacebookButton;

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
                   // goHome();
                }else{
                    Log.w(TAG, "User Not Logged.");
                }
            }
        };

        // for testing app
        this.mockData();

        // setup sdk facebook
        this.setupSDKFacebook();
    }

    private void setupSDKFacebook() {
        // sdk facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        this.callbackManager = CallbackManager.Factory.create();

        this.loginFacebookButton = (LoginButton) findViewById(R.id.login_facebook_button);
        this.loginFacebookButton.setReadPermissions(Arrays.asList("email"));

        this.loginFacebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Log.w(TAG, "Login with facebook successfull: " + accessToken.getApplicationId());
                signInFacebookWithFirebase(accessToken);
            }

            @Override
            public void onCancel() {
                Log.w(TAG, "Login with facebook has cancel by user");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w(TAG, "Login with facebook has cancel by user: " + error.toString());
                error.getStackTrace();
                loginError(error.toString());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void signInFacebookWithFirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    goHome();
                }
            }
        });
    }

    private void mockData() {
        this.usernameEditText.setText("achamorro@coresystems.io");
        this.passwordEditText.setText("22122009");
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
        //insertData();
        //readData();
        loginPresenter.signIn(username, password, this, firebaseAuth);
    }

    private void insertData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ManagmentDatabase.UserTable.COLUMN_USERNAME, "usuario de prueba");
        values.put(ManagmentDatabase.UserTable.COLUMN_EMAIL, "correo@dominio");
        values.put(ManagmentDatabase.UserTable.COLUMN_PASSWORD, "password123$");
        values.put(ManagmentDatabase.UserTable.COLUMN_CREATED_DATE, new Date().toString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ManagmentDatabase.UserTable.TABLE_NAME, null, values);
        Log.w("LOGIN ACTIVIY", "primary key -> " + newRowId);
    }

    private void readData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ManagmentDatabase.UserTable.COLUMN_USERNAME,
                ManagmentDatabase.UserTable.COLUMN_EMAIL
        };

        // Filter results WHERE "title" = 'My Title'
        String selection =  ManagmentDatabase.UserTable.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { "usuario de prueba" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = ManagmentDatabase.UserTable.COLUMN_USERNAME + " DESC";

        Cursor cursor = db.query(
                ManagmentDatabase.UserTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<User> users = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(ManagmentDatabase.UserTable._ID));

            User user = new User();
            //user.setId(cursor.getInt(cursor.getColumnIndex(ManagmentDatabase.UserTable._ID)));
            user.setId((int) id);
            user.setUserName(cursor.getString(cursor.getColumnIndex(ManagmentDatabase.UserTable.COLUMN_USERNAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(ManagmentDatabase.UserTable.COLUMN_EMAIL)));

            users.add(user);
        }
        cursor.close();

    }

    @Override
    protected void onDestroy() {
        // close database
        super.onDestroy();
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
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
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
