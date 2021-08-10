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
    private FirebaseUser currentUser;

    public AuthRepository() {
        mAuth = FirebaseAuth.getInstance();
        userMutableLiveData = new MutableLiveData<>();

        currentUser = mAuth.getCurrentUser();
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
                        Timber.d("signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        userMutableLiveData.postValue(user);
                        Timber.d("User %s", user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w(task.getException(), "signInWithCredential:failure");
                        Timber.d("User %s", (Object) null);
                    }
                });
    }

    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d( "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        userMutableLiveData.postValue(user);
                        Timber.d("User %s", user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w(task.getException(), "signInWithCredential:failure");
//                            updateUI(null);
                        Timber.d("User %s", (Object) null);
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
        userMutableLiveData.postValue(mAuth.getCurrentUser());
        Timber.d("User %s", mAuth.getCurrentUser());
    }
}


