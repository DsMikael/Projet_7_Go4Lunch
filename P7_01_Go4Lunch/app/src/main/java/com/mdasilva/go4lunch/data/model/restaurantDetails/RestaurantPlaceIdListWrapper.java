package com.mdasilva.go4lunch.data.model.restaurantDetails;

import com.squareup.moshi.Json;

import java.util.List;

public class RestaurantPlaceIdListWrapper {

    @Json(name = "results")
    private List<RestaurantPlaceIdList> restaurantPlaceIdList = null;

    public List<RestaurantPlaceIdList> getRestaurantPlaceIdList(){
        return restaurantPlaceIdList;
    }

    public void setRestaurantPlaceIdList(List<RestaurantPlaceIdList> restaurantPlaceIdList){
        this.restaurantPlaceIdList = restaurantPlaceIdList;
    }

}
