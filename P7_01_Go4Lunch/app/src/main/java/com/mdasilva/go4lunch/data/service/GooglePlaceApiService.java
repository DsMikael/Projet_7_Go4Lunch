package com.mdasilva.go4lunch.data.service;

import com.mdasilva.go4lunch.data.model.RestaurantListWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceApiService {

    @GET("maps/api/place/nearbysearch/json?")
    Call<RestaurantListWrapper> getRestaurants(@Query("location") String location,
                                                     @Query("radius") int radius,
                                                     @Query("type") String type,
                                                     @Query("key") String key);
}
