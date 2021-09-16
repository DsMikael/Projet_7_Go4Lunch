package com.mdasilva.go4lunch.data.repository;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.mdasilva.go4lunch.data.model.RestaurantDetails;
import com.mdasilva.go4lunch.data.model.RestaurantListWrapper;
import com.mdasilva.go4lunch.data.service.GooglePlaceApiService;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

public class GooglePlaceRepository {
    private final GooglePlaceApiService mApiService;
    private List<RestaurantDetails> restaurantDetails;

    public GooglePlaceRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build()))
                .build();

        mApiService = retrofit.create(GooglePlaceApiService.class);
    }

    public List<RestaurantDetails> getRestaurants(String location,
                                                      int radius,
                                                      String type,
                                                      String key) {


        mApiService.getRestaurants(location, radius, type, key).enqueue(
                new Callback<RestaurantListWrapper>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantListWrapper> call,
                                   @NonNull Response<RestaurantListWrapper> response) {
                Timber.d(call.toString());
                if (response.body() != null) {
                    Timber.d(response.body().getRestaurantDetails().get(1).getName());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantListWrapper> call,
                                  @NonNull Throwable t) {
                Timber.d(String.valueOf(call.request()));
                Timber.w(t, "Something went wrong!");
            }
        });
        return restaurantDetails;
    }

}
