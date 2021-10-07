package com.mdasilva.go4lunch.data.model;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {

    public String username;
    public Uri userImage;
    public String idRestaurant;


    public User(String username, Uri userImage, String idRestaurant) {
        this.username = username;
        this.userImage = userImage;
        this.idRestaurant = idRestaurant;
    }

    public User(String username, Uri userImage) {
        this.username = username;
        this.userImage = userImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Uri getUserImage() {
        return userImage;
    }

    public void setUserImage(Uri userImage) {
        this.userImage = userImage;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userImage='" + userImage + '\'' +
                ", idRestaurant='" + idRestaurant + '\'' +
                '}';
    }
}
