package com.mdasilva.go4lunch.data.service;

import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetailsWrapper;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantPlaceIdListWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceApiService {

    @GET("nearbysearch/json")
    Call<RestaurantPlaceIdListWrapper> getRestaurants(@Query("location") String location,
                                                      @Query("radius") int radius,
                                                      @Query("type") String type,
                                                      @Query("key") String key);


    @GET("details/json")
    Call<RestaurantDetailsWrapper> getRestaurantsDetails(@Query("place_id") String placeId,
                                                         @Query("key") String key);

}
