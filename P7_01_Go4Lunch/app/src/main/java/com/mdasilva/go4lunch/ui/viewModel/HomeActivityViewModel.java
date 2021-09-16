package com.mdasilva.go4lunch.ui.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.auth.FirebaseUser;
import com.mdasilva.go4lunch.data.model.RestaurantDetails;
import com.mdasilva.go4lunch.data.model.RestaurantListWrapper;
import com.mdasilva.go4lunch.data.repository.AuthRepository;
import com.mdasilva.go4lunch.data.repository.GooglePlaceRepository;

import java.util.List;

import timber.log.Timber;

public class HomeActivityViewModel extends AndroidViewModel {

    private final AuthRepository mAuthRepo;
    public final MutableLiveData<Location> mlocation = new MutableLiveData<>();
    public GooglePlaceRepository mGoogleRepos;

    public HomeActivityViewModel(@NonNull Application application) {
        super(application);
        mAuthRepo = new AuthRepository();
        mGoogleRepos = new GooglePlaceRepository();
        PlacesClient placesClient = Places.createClient(application.getApplicationContext());
//       placeFields = Arrays.asList(
//                Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG,
//                Place.Field.OPENING_HOURS, Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI,
//                Place.Field.TYPES, Place.Field.PHOTO_METADATAS);
//
//        request = FindCurrentPlaceRequest.newInstance(placeFields);


    }



    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return mAuthRepo.getUserMutableLiveData();
    }

    public void signOut() {
        mAuthRepo.signOut();
    }

    public void location(Location lastKnownLocation) {
        mlocation.postValue(lastKnownLocation);
        Timber.d("Location %s" , lastKnownLocation);
        getRestaurantDetails(lastKnownLocation);
    }

    @SuppressLint("MissingPermission")
    public void getRestaurantDetails(Location location){
        List<RestaurantDetails> listRestaurant = mGoogleRepos.getRestaurants(
                location.getLatitude()+","+location.getLongitude(),
                1500,
                "restaurant",
                "AIzaSyDvX-bwM5ZRMI8nRUx58ZDvqVQLzl7z9os");

        Timber.d(String.valueOf(listRestaurant));
//
//        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID,
//                Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG,
////                Place.Field.OPENING_HOURS, Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI,
//                Place.Field.TYPES, Place.Field.PHOTO_METADATAS);
//
//
//        FindCurrentPlaceRequest request =
//                FindCurrentPlaceRequest.builder(placeFields).build();

//
//        placesClient.fetchPlace(request).addOnSuccessListener(((response) -> {
//            for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
//                Timber.d("Place '%s' has likelihood: %f",
//                        placeLikelihood.getPlace().getName(),
//                        placeLikelihood.getLikelihood());
//            }
//        })).addOnFailureListener((exception) -> {
//            if (exception instanceof ApiException) {
//                ApiException apiException = (ApiException) exception;
//                Timber.e("Place not found: %s", apiException.getStatusCode());
//            }
//        });

    }

//        mRestaurantList = new RestaurantDetails(
//                place_id = ,
//                types= ,
//                name= ,
//                phone= ,
//                address= ,
//                website= ,
//                rating= ,
//                location
//        );


    }

