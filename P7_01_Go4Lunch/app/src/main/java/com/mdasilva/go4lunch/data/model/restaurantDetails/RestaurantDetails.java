package com.mdasilva.go4lunch.data.model.restaurantDetails;

import com.squareup.moshi.Json;

public class RestaurantDetails {

    //Place detail
    @Json(name = "name")
    private String name;

    @Json(name = "formatted_address")
    private String address;

    @Json(name = "formatted_phone_number")
    private String phoneNumber;

    @Json(name = "place_id")
    private String placeId;

    @Json(name = "website")
    private String website;


    public RestaurantDetails(String name, String address, String phoneNumber, String placeId, String website) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.placeId = placeId;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    @Override
    public String toString() {
        return "RestaurantDetails{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", placeId='" + placeId + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}