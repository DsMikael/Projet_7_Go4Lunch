package com.mdasilva.go4lunch.ui.view;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.mdasilva.go4lunch.R;
import com.mdasilva.go4lunch.data.repository.GooglePlaceRepository;
import com.mdasilva.go4lunch.databinding.MapsFragmentBinding;
import com.mdasilva.go4lunch.ui.viewModel.HomeActivityViewModel;

import timber.log.Timber;

public class MapsFragment extends Fragment
        implements OnMapReadyCallback{

    private MapsFragmentBinding binding;
    private HomeActivityViewModel viewModel;
    public GooglePlaceRepository mGoogleRepos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MapsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity())
                .get(HomeActivityViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.maps_fragment);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        try {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        mMap.addMarker(new MarkerOptions().position(map).title("Me"));
//        mMap.setMaxZoomPreference(16.0f);
        viewModel.mlocation.observe(getViewLifecycleOwner(), location -> {
            googleMap.setMinZoomPreference(16.0f);
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(
                    new LatLng(location.getLatitude(),location.getLongitude())));
            LatLng mlocation = new LatLng(location.getLatitude(),location.getLongitude());
            Timber.d(mlocation.toString());
            mGoogleRepos.getRestaurants(
                    mlocation,
                    1500,
                    "restaurant",
                    "AIzaSyDvX-bwM5ZRMI8nRUx58ZDvqVQLzl7z9os");
        });
    }


}
