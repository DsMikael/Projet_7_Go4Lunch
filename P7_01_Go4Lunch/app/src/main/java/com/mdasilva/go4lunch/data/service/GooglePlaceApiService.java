package com.mdasilva.go4lunch.data.service;

import com.google.android.gms.maps.model.LatLng;
import com.mdasilva.go4lunch.data.model.RestaurantDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceApiService {

    @GET("maps/api/place/nearbysearch/json")
    Call<List<RestaurantDetails>> getRestaurants(@Query("location") LatLng location,
                                                 @Query("radius") int radius,
                                                 @Query("type") String type,
                                                 @Query("key") String key);
}
