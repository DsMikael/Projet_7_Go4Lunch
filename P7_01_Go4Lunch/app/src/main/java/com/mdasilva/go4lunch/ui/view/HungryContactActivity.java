package com.mdasilva.go4lunch.ui.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.mdasilva.go4lunch.data.model.restaurantDetails.RestaurantDetails;
import com.mdasilva.go4lunch.databinding.ActivityHungryContactBinding;
import com.mdasilva.go4lunch.ui.viewModel.HungryContactViewModel;


public class HungryContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.mdasilva.go4lunch.databinding.ActivityHungryContactBinding binding = ActivityHungryContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        HungryContactViewModel viewModel = new ViewModelProvider(this).get(HungryContactViewModel.class);


        RestaurantDetails restaurant = (RestaurantDetails) getIntent().getSerializableExtra("restaurant");
        Uri RestaurantUrl = viewModel.getUriPhoto(restaurant.getPhotos().get(0).getPhotoReference());
        Glide.with(this).load(RestaurantUrl).centerCrop().into(binding.itemListAvatar);

        binding.itemHungryName.setText(restaurant.getName());
        binding.itemHungryAddress.setText(restaurant.getAddress());

        binding.itemHungryCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.fromParts("tel",  restaurant.getPhoneNumber(), null));
            startActivity(intent);
        });

        binding.itemHungryWebsite.setOnClickListener(v -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(restaurant.getWebsite()));
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void navigate(Context context, RestaurantDetails restaurant) {
        Intent intent = new Intent(context, HungryContactActivity.class);
        intent.putExtra("restaurant", restaurant);
        ActivityCompat.startActivity(context, intent, null);
    }

}
