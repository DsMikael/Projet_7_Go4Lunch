package com.mdasilva.go4lunch.data.model.restaurantDetails;

import com.squareup.moshi.Json;
public class RestaurantDetailsWrapper {

    @Json(name = "result")
    private RestaurantDetails restaurantDetails;

    public RestaurantDetails getRestaurantDetails(){
        return restaurantDetails;
    }

    public void setRestaurantDetailsList(RestaurantDetails restaurantDetails){
        this.restaurantDetails = restaurantDetails;
    }
}
