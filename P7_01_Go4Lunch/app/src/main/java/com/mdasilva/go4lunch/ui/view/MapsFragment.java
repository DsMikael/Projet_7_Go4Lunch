package com.mdasilva.go4lunch.ui.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.mdasilva.go4lunch.R;
import com.mdasilva.go4lunch.databinding.MapsFragmentBinding;

import timber.log.Timber;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private MapsFragmentBinding binding;
    private LocationManager locationManager;
    private GoogleMap mMap;
    double longitude;
    double latitude;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MapsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.maps_fragment);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        locationManager = (LocationManager) requireContext()
                .getSystemService(Context.LOCATION_SERVICE);


        boolean coarseLocation = ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean fineLocation = ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        Timber.d("Permission ACCESS_COARSE_LOCATION %s", coarseLocation);
        Timber.d("Permission ACCESS_FINE_LOCATION %s", fineLocation);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        checkLocation();
        LatLng map = new LatLng(latitude, longitude);
//        mMap.addMarker(new MarkerOptions().position(map).title("Me"));
//        mMap.setMaxZoomPreference(16.0f);
        mMap.setMinZoomPreference(16.0f);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(map));
    }

    public void checkLocation(){
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            Timber.d("Loc " + loc.getLongitude() + " et " + loc.getLatitude());
            mMap.setMyLocationEnabled(true);
        }


    }

}