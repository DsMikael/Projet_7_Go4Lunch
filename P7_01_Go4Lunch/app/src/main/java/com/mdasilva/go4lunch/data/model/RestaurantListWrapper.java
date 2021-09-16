package com.mdasilva.go4lunch.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class RestaurantListWrapper {

    @Json(name = "result")
    private List<RestaurantDetails> restaurantDetailsList = null;

    public List<RestaurantDetails> getRestaurantDetails(){
        return restaurantDetailsList;
    }

    public void setRestaurantDetailsList(List<RestaurantDetails> restaurantDetailsList){
        this.restaurantDetailsList = restaurantDetailsList;
    }

}
