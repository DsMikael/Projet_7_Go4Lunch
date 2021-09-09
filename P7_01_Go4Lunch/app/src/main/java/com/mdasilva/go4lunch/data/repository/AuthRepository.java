package com.mdasilva.go4lunch.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import timber.log.Timber;

public class AuthRepository{

    private final FirebaseAuth mAuth;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;

    public AuthRepository() {
        mAuth = FirebaseAuth.getInstance();
        userMutableLiveData = new MutableLiveData<>();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        userMutableLiveData.postValue(currentUser);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void handleFacebookAccessToken(AccessToken token) {
        Timber.d("handleFacebookAccessToken:%s", token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        userMutableLiveData.postValue(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w(task.getException(), "signInWithCredential:failure");
                    }
                });
    }

    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        userMutableLiveData.postValue(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w(task.getException(), "signInWithCredential:failure");
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
        userMutableLiveData.postValue(mAuth.getCurrentUser());
    }


}


