package com.mdasilva.go4lunch.data.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.mdasilva.go4lunch.data.model.RestaurantDetails;
import com.mdasilva.go4lunch.data.service.GooglePlaceApiService;
import com.squareup.moshi.Moshi;

import java.util.List;

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

    public List<RestaurantDetails> getRestaurants(LatLng location,
                                                  int radius,
                                                  String type,
                                                  String key) {
        Timber.d(location+" "+ radius+" "+type+" "+key);

        mApiService.getRestaurants(location, radius, type, key).enqueue(new Callback<List<RestaurantDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<RestaurantDetails>> call, @NonNull Response<List<RestaurantDetails>> response) {
                Timber.d(call.toString());
                Timber.d(response.toString());
                restaurantDetails = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<List<RestaurantDetails>> call, @NonNull Throwable t) {
                Timber.w(t.getCause(), "Something went wrong!");
            }
        });
        return restaurantDetails;
    }

}
