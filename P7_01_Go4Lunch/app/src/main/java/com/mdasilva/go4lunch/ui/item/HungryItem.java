package com.mdasilva.go4lunch.ui.item;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.mdasilva.go4lunch.R;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetails;
import com.mdasilva.go4lunch.data.repository.GooglePlaceRepository;
import com.mdasilva.go4lunch.databinding.HungryFragmentBinding;
import com.mdasilva.go4lunch.ui.view.HungryContactActivity;
import com.xwray.groupie.viewbinding.BindableItem;

public class HungryItem extends BindableItem<HungryFragmentBinding> {

    private final RestaurantDetails mRestaurant;
    private final Location mlocation;
    private final Context mArgument;

    public HungryItem(RestaurantDetails restaurant, Location location, Context context){
        mRestaurant = restaurant;
        mlocation = location;
        mArgument = context;
    }

    @NonNull
    @Override
    protected HungryFragmentBinding initializeViewBinding(@NonNull View view) {
        return HungryFragmentBinding.bind(view);
    }

    @Override
    public void bind(@NonNull HungryFragmentBinding viewBinding, int position) {

        float[] result = new float[1];
        Location.distanceBetween(mlocation.getLatitude(),mlocation.getLongitude(),
                Double.parseDouble(mRestaurant.getGeometry().getLocation().getLat()),
                Double.parseDouble(mRestaurant.getGeometry().getLocation().getLng()), result);

        String distance = mArgument.getString(R.string.itemListHungryRange, String.valueOf(Math.round(result[0])));

        String Mates = mArgument.getString(R.string.itemListHungryMates, String.valueOf(0));//TODO Nombres Collaborateurs

        viewBinding.itemListHungryName.setText(mRestaurant.getName());
        viewBinding.itemListHungryAddress.setText(mRestaurant.getAddress());

        String openHours =  "---";
        if(mRestaurant.getOpeningHours() != null ){
            if (mRestaurant.getOpeningHours().getOpenNow() != null) {
                if (mRestaurant.getOpeningHours().getOpenNow()) {
                    openHours =  "Closed";
                }else{
                    openHours =  "Open until";
                }
            }
        }
        viewBinding.itemListHungryOpening.setText(openHours);
        viewBinding.itemListHungryRange.setText(distance);
        viewBinding.itemListHungryMates.setText(Mates);


        if(mRestaurant.getPhotos() != null) {
//            Timber.d(String.valueOf(mRestaurant.getPhotos().get(0).getPhotoReference()));
            Uri restaurantUrl = new GooglePlaceRepository().getRestaurantUrl(mRestaurant.getPhotos().get(0).getPhotoReference());
            Glide.with(viewBinding.getRoot()).load(restaurantUrl).centerCrop().into(viewBinding.itemListHungryAvatar);
        }else{
            viewBinding.itemListHungryAvatar.setImageResource(R.drawable.ic_home_hungry_list);
        }


        viewBinding.itemList.setOnClickListener(v -> HungryContactActivity.navigate(v.getContext(), mRestaurant));

    }

    @Override
    public int getLayout() {
        return R.layout.hungry_fragment;
    }
}
