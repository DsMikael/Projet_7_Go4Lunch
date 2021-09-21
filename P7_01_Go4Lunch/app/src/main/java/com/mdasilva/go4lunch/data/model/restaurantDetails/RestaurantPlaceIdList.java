package com.mdasilva.go4lunch.data.model.restaurantDetails;

import com.squareup.moshi.Json;

public class RestaurantPlaceIdList {

    @Json(name = "place_id")
    private String placeid;

    public RestaurantPlaceIdList(String placeid){
        this.placeid = placeid;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    @Override
    public String toString() {
        return "RestaurantPlaceIdList{" +
                "placeid='" + placeid + '\'' +
                '}';
    }
}
