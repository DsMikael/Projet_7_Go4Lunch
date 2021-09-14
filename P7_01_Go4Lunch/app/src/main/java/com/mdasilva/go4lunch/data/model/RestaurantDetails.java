package com.mdasilva.go4lunch.data.model;

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

    public RestaurantDetails(LatLng location, String name, Boolean openingHours, String photoReference, String placeid, String vicinity) {
        this.location = location;
        this.name = name;
        this.openingHours = openingHours;
        this.photoReference = photoReference;
        this.placeid = placeid;
        this.vicinity = vicinity;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Boolean openingHours) {
        this.openingHours = openingHours;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

//    //Place detail
//    private String phone;
//    private String address;
//    private String website;
//    private Uri photo;
//
//    //Firebase
//    private double rating;




}