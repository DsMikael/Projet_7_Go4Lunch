package com.mdasilva.go4lunch.data.model;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.moshi.Json;

public class RestaurantDetails {

    //Nearbysearch Google
    @Json(name = "location")
    private LatLng location;
    @Json(name = "name")
    private String name;
    @Json(name = "opening_hours")
    private Boolean openingHours;
    @Json(name = "photo_reference")
    private String photoReference;
    @Json(name = "place_id")
    private String placeid;
    @Json(name = "vicinity")
    private String vicinity;

    //Place detail
    private String phone;
    private String address;
    private String website;
    private Uri photo;

    //Firebase
    private double rating;

}