package com.mdasilva.go4lunch.ui.viewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.mdasilva.go4lunch.data.repository.GooglePlaceRepository;

public class HungryContactViewModel extends AndroidViewModel {

    public HungryContactViewModel(@NonNull Application application) {
        super(application);
    }

    public Uri getUriPhoto(String photoReference) {
        return new GooglePlaceRepository().getRestaurantUrl(photoReference);
    }
}
