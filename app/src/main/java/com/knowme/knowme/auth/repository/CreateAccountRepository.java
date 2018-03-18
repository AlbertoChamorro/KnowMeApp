package com.knowme.knowme.auth.repository;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.knowme.knowme.auth.presenter.ICreateAccountPresenter;
import com.knowme.knowme.model.User;

/**
 * Created by coredeveloper on 3/17/18.
 */

public class CreateAccountRepository implements ICreateAccountRepository {

    private ICreateAccountPresenter createAccountPresenter;
    private FirebaseAuth firebaseAuth;

    public CreateAccountRepository(ICreateAccountPresenter createAccountPresenter) {
        this.createAccountPresenter = createAccountPresenter;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void createAccount(User user, Activity activity) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                createAccountPresenter.createAccountSuccess("Cuenta creada correctamente.");
                            }else{
                                createAccountPresenter.createAccountError("Ocurrio un error intente de nuevo más tarde.");
                            }
                        }
                    });
    }
}
