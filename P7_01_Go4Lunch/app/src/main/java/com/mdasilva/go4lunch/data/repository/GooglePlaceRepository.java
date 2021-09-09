package com.mdasilva.go4lunch.data.repository;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.mdasilva.go4lunch.data.model.RestaurantDetails;
import com.mdasilva.go4lunch.data.service.GooglePlaceApiService;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class GooglePlaceRepository {
    private GooglePlaceApiService mApiService;

    public GooglePlaceRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build()))
                .build();

        mApiService = retrofit.create(GooglePlaceApiService.class);
    }

    public List<RestaurantDetails> getRestaurants(LatLng location,
                                                  int radius,
                                                  String type,
                                                  String key) throws IOException {

        mApiService.getRestaurants(location, radius, type, key);
    //.execute();

        return null;
    }

}
