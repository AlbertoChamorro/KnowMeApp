package com.knowme.knowme.auth.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.knowme.knowme.auth.presenter.ILoginPresenter;


/**
 * Created by coredeveloper on 3/11/18.
 */

public class LoginRepository implements ILoginRepository {

    private ILoginPresenter loginPresenter;

    public LoginRepository(ILoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void signIn(final String username,String password,final Activity activity,FirebaseAuth firebaseAuth) {

        // code test
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                boolean success =  true;
//                if(success){
//                    loginPresenter.signInSuccess();
//                    return;
//                }
//                loginPresenter.signInError("Intente luego, ha ocurrido un error.");
//            }
//        }, 3000);
        // end code test

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    SharedPreferences sp = activity.getSharedPreferences("UserSingIn",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", task.getResult().getUser().getEmail());
                    editor.commit();

                    // sp.getAll() || sp.getString("email", "nulo") || sp.getInt("email", 2)

                    loginPresenter.signInSuccess();
                    return;
                }
                loginPresenter.signInError(task.getException().getMessage());
            }
        });
    }
}
