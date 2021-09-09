package com.mdasilva.go4lunch.data.model;

import android.net.Uri;

public class User {

    public String username;
    public String email;
    public Uri userImage;


    public User(String username, String email, Uri userImage) {
        this.username = username;
        this.email = email;
        this.userImage = userImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getUserImage() {
        return userImage;
    }

    public void setUserImage(Uri userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userImage=" + userImage +
                '}';
    }
}
