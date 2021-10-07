package com.mdasilva.go4lunch.data.repository;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetails;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetailsPhoto;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetailsWrapper;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantPlaceIdList;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantPlaceIdListWrapper;
import com.mdasilva.go4lunch.data.service.GooglePlaceApiService;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

public class GooglePlaceRepository {


    public interface ResultListener
    {
        void onResult(List<RestaurantDetails> restaurant);
    }

    public interface ResultDetailListener
    {
        void onResult(RestaurantDetails restaurant);
    }

    private final GooglePlaceApiService mApiService;
    private static final String KEY_API = "AIzaSyDvX-bwM5ZRMI8nRUx58ZDvqVQLzl7z9os";

    public GooglePlaceRepository(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();
                final HttpUrl key = url.newBuilder().addQueryParameter("key", KEY_API).build();
                final Request build = request.newBuilder().url(key).build();

                return chain.proceed(build);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build()))
                .client(okHttpClient)
                .build();

        mApiService = retrofit.create(GooglePlaceApiService.class);


    }
    public Uri getRestaurantUrl(String photoRefence){
        return new Uri.Builder().scheme("https").authority("maps.googleapis.com")
                .path("/maps/api/place/photo")
                .appendQueryParameter("maxwidth", String.valueOf(400))
                .appendQueryParameter("photo_reference", photoRefence)
                .appendQueryParameter("key", KEY_API)
                .build();
    }

    public void getRestaurants(String location, int radius, String type,
                               ResultListener resultListener) {

        List<RestaurantDetails> restaurantDetails = new ArrayList<>();
        mApiService.getRestaurants(location, radius, type).enqueue(
                new Callback<RestaurantPlaceIdListWrapper>() {
                    @Override
                    public void onResponse(@NonNull Call<RestaurantPlaceIdListWrapper> call,
                                           @NonNull Response<RestaurantPlaceIdListWrapper> response) {

                        if (response.body() != null) {
                            final AtomicInteger index = new AtomicInteger(0);
                            for (RestaurantPlaceIdList restaurantPlaceId :
                                    response.body().getRestaurantPlaceIdList()){
                                getRestaurantsDetails(restaurantPlaceId.getPlaceid() , restaurant -> {
                                    index.set(index.get() + 1);
                                    if (restaurant != null){
                                        restaurantDetails.add(restaurant);
                                    }

                                    if (index.get() == response.body().getRestaurantPlaceIdList().size()){
                                        resultListener.onResult(restaurantDetails);
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

    public void getRestaurantsDetails(String restaurantPlaceId,
                                  ResultDetailListener resultDetailListener){

        mApiService.getRestaurantsDetails(restaurantPlaceId).enqueue(
                new Callback<RestaurantDetailsWrapper>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantDetailsWrapper> call,
                                   @NonNull Response<RestaurantDetailsWrapper> response) {
                final RestaurantDetails restaurant;
                if (response.body() != null) {
                    Timber.d(String.valueOf(call.request()));
                    restaurant = response.body().getRestaurantDetails();
                    resultDetailListener.onResult(restaurant);

                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantDetailsWrapper> call,
                                  @NonNull Throwable t) {
                Timber.w(t);
                resultDetailListener.onResult(null);
            }
        });

    }


}
