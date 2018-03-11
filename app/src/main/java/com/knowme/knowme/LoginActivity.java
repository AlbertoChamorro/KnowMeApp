package com.knowme.knowme;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.knowme.knowme.View.CreateAccountActivity;
import com.knowme.knowme.View.MainActivity;

import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void createNewAccount(View view) {
        Intent createAccountActivity = new Intent(this, CreateAccountActivity.class);
        startActivity(createAccountActivity);
    }

    public void login(View view) {
       Intent homeActivity = new Intent(this, MainActivity.class);
       startActivity(homeActivity);
    }

    public void redirectToWebPage(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.marca.com"));
        startActivity(intent);
    }
}
