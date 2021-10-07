package com.mdasilva.go4lunch.ui.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetails;
import com.mdasilva.go4lunch.data.repository.AuthRepository;
import com.mdasilva.go4lunch.data.repository.GooglePlaceRepository;

import java.util.List;

public class HomeActivityViewModel extends AndroidViewModel {

    private final AuthRepository mAuthRepo;
    public final MutableLiveData<Location> mlocation = new MutableLiveData<>();
    public GooglePlaceRepository mGoogleRepos;
    public MutableLiveData<List<RestaurantDetails>> restaurantDetailsList = new MutableLiveData<>();


    public HomeActivityViewModel(@NonNull Application application) {
        super(application);
        mAuthRepo = new AuthRepository();
        mGoogleRepos = new GooglePlaceRepository();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return mAuthRepo.getUserMutableLiveData();
    }

    public void signOut() {
        mAuthRepo.signOut();
    }

    public void location(Location lastKnownLocation) {
        mlocation.postValue(lastKnownLocation);
        getRestaurantDetails(lastKnownLocation);
    }

    @SuppressLint("MissingPermission")
    public void getRestaurantDetails(Location location) {
        mGoogleRepos.getRestaurants(
                location.getLatitude() + "," + location.getLongitude(),
                500,
                "restaurant",
                restaurants -> {
                    if(restaurants != null && restaurants.size() != 0){
                        restaurantDetailsList.postValue(restaurants);
                    }
                });
    }
}

