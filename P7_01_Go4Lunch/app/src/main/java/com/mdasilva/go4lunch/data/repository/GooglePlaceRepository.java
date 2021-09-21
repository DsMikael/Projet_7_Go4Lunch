package com.mdasilva.go4lunch.data.repository;

import androidx.annotation.NonNull;

import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetails;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetailsWrapper;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantPlaceIdList;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantPlaceIdListWrapper;
import com.mdasilva.go4lunch.data.service.GooglePlaceApiService;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

public class GooglePlaceRepository {

    public interface ResultListener{
        void onResult(List<RestaurantDetails> restaurant);
    }

    private final GooglePlaceApiService mApiService;

    public GooglePlaceRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build()))
                .build();

        mApiService = retrofit.create(GooglePlaceApiService.class);

    }

    public void getRestaurants(String location, int radius,
                                                  String type, String key, ResultListener resultListener) {

        List<RestaurantDetails> restaurantDetails = new ArrayList<>();
        mApiService.getRestaurants(location, radius, type, key).enqueue(
                new Callback<RestaurantPlaceIdListWrapper>() {
                    @Override
                    public void onResponse(@NonNull Call<RestaurantPlaceIdListWrapper> call,
                                           @NonNull Response<RestaurantPlaceIdListWrapper> response) {

                        if (response.body() != null) {
                            for (RestaurantPlaceIdList restaurantPlaceId : response.body().getRestaurantPlaceIdList()){
                                mApiService.getRestaurantsDetails(restaurantPlaceId.getPlaceid(),key)
                                        .enqueue(new Callback<RestaurantDetailsWrapper>() {
                                    @Override
                                    public void onResponse(@NonNull Call<RestaurantDetailsWrapper> call,
                                                           @NonNull Response<RestaurantDetailsWrapper> response) {
                                        final RestaurantDetails restaurant = response.body().getRestaurantDetails();
                                        restaurantDetails.add(restaurant);
                                    }
                                    @Override
                                    public void onFailure(@NonNull Call<RestaurantDetailsWrapper> call,
                                                          @NonNull Throwable t) {
                                        Timber.d(String.valueOf(t));
                                    }
                                });
                            }
                        }
                        resultListener.onResult(restaurantDetails);
                    }
                    @Override
                    public void onFailure(@NonNull Call<RestaurantPlaceIdListWrapper> call,
                                          @NonNull Throwable t) {
                        Timber.w(t, "Something went wrong on RestaurantPlaceIdListWrapper!");
                        resultListener.onResult(null);
                    }
                });
    }
}
