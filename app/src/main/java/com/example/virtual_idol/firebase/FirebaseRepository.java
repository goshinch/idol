package com.example.virtual_idol.firebase;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.virtual_idol.components.LOG;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseRepository implements LOG {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseSignMutableData;
    private FirebaseAuth mAuth;
    private CallbackException callbackException;
    private CallbackSignUpSuccess callbackSignUpSuccess;

    public FirebaseRepository(Application application) {
        this.application = application;
        firebaseSignMutableData = new MutableLiveData<>();

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null)
            firebaseSignMutableData.postValue(mAuth.getCurrentUser());
    }

    private void updateUserProfile(FirebaseUser user, String name, Uri uri) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .setPhotoUri(uri)
                .build();

    }

    public void signUp(String userId, String password, String name) {
        mAuth.createUserWithEmailAndPassword(userId, password)
            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUserProfile(user, name, null);
                        sendSuccess();
                    } else {
                        sendException(task.getException().toString());
                        // If sign in fails, display a message to the user.
                    }
                }
            });
    }

    public void logIn(String email, String password) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                sendSuccess();
                            } else {
                                // If sign in fails, display a message to the user.
                                sendException(task.getException().toString());
                            }
                        }
                    });
    }

    public void setCallbackSignUpSuccess(CallbackSignUpSuccess callbackSignUpSuccess) {
        this.callbackSignUpSuccess = callbackSignUpSuccess;
    }

    public void sendSuccess() {
        if (callbackSignUpSuccess != null)
            callbackSignUpSuccess.signUpSuccess();
    }

    public void setCallbackException(CallbackException callbackException) {
        this.callbackException = callbackException;
    }

    public void sendException(String exception) {
        if (callbackException != null)
            callbackException.signUpException(exception);
    }
}
