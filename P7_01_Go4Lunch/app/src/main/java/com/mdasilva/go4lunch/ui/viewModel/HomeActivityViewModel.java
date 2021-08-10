package com.mdasilva.go4lunch.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.mdasilva.go4lunch.data.repository.AuthRepository;

public class HomeActivityViewModel extends AndroidViewModel {

    private final AuthRepository mAuthRepo;

    public HomeActivityViewModel(@NonNull Application application) {
        super(application);
        mAuthRepo = new AuthRepository();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return mAuthRepo.getUserMutableLiveData();
    }
}
