package com.mdasilva.go4lunch.ui.viewModel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.mdasilva.go4lunch.data.repository.AuthRepository;
import com.mdasilva.go4lunch.ui.view.LoginActivity;

import java.util.Arrays;

import timber.log.Timber;

public class LoginActivityViewModel extends AndroidViewModel {

    private final AuthRepository mAuthRepo;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        mAuthRepo = new AuthRepository();
    }

    public void signInFacebook(LoginActivity loginActivity) {
        Timber.d("Facebook signIn");
        LoginManager.getInstance().logInWithReadPermissions(loginActivity,
                Arrays.asList("email", "public_profile"));


    }

    public void signInGoogle(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Timber.d( "firebaseAuthWithGoogle: %s", account.getId());
            firebaseAuthWithGoogle(account.getIdToken());

        } catch (ApiException e) {
            // Google Sign In failed, update UI appropriately
            Timber.w(e, "Google sign in failed");
        }
    }

    public void firebaseAuthWithGoogle(String idToken) {
        mAuthRepo.firebaseAuthWithGoogle(idToken);
    }


    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return mAuthRepo.getUserMutableLiveData();
    }

    public void signOut() {
        mAuthRepo.signOut();
    }

    public void handleFacebookAccessToken(AccessToken accessToken) {
        mAuthRepo.handleFacebookAccessToken(accessToken);
    }
}
