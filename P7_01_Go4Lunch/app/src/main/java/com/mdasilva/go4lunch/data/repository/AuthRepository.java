package com.mdasilva.go4lunch.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mdasilva.go4lunch.data.model.User;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class AuthRepository{

    private final FirebaseAuth mAuth;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;
    private final FirebaseFirestore database;

    private final Map<String, Object> user = new HashMap<>();

    public AuthRepository() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
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

    public void addUser(FirebaseUser firebaseUser){

        user.put("idRestaurant", null);
        user.put("name", firebaseUser.getDisplayName());
        user.put("photo", firebaseUser.getPhotoUrl());

//        User user = new User(firebaseUser.getDisplayName(),firebaseUser.getPhotoUrl());

        Timber.d(String.valueOf(user));
        DocumentReference userDocumentRef = database.collection("mates")
                .document("mate");

        userDocumentRef.set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Timber.d("DocumentSnapshot successfully written!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Timber.w(e, "Error writing document");
            }
        });

    }


}


