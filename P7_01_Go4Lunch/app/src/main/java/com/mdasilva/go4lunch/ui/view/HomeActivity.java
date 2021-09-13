package com.mdasilva.go4lunch.ui.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mdasilva.go4lunch.BuildConfig;
import com.mdasilva.go4lunch.R;
import com.mdasilva.go4lunch.databinding.ActivityHomeBinding;
import com.mdasilva.go4lunch.ui.viewModel.HomeActivityViewModel;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import timber.log.Timber;

@RuntimePermissions
public class HomeActivity extends AppCompatActivity{

    private ActivityHomeBinding binding;
    private LocationManager locationManager;
    private HomeActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        viewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);

//        FusedLocationProviderClient fusedLocationProviderClient =
//                LocationServices.getFusedLocationProviderClient(this);

        setSupportActionBar(binding.myToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        binding.drawerLayout.addDrawerListener(toggle);

        binding.navBottomMenu.setOnItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case (R.id.navigation_hungry_list):
                    fragment = new HungryListFragment();
                    break;
                case (R.id.navigation_mates_list):
                    fragment = new MatesListFragment();
                    break;
                default:
                    fragment = new MapsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, fragment, null)
//                    .commitAllowingStateLoss();
                    .commit();

            return true;
        });

        binding.navView.bringToFront();
        binding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_menu_lunch) {
                Toast.makeText(HomeActivity.this, "Home",
                        Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_menu_settings) {
                Toast.makeText(HomeActivity.this, "The SETTING",
                        Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_menu_logout) {
                viewModel.signOut();
                startActivity(new Intent(this, LoginActivity.class));
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        viewModel.getUserMutableLiveData().observe(this, profile -> {
            if(profile != null) {
                View headerView = binding.navView.getHeaderView(0);
                TextView name = headerView.findViewById(R.id.drawer_name);
                TextView email = headerView.findViewById(R.id.drawer_mail);
                ImageView userImage = headerView.findViewById(R.id.imageView);
                name.setText(profile.getDisplayName());
                email.setText(profile.getEmail());
                Glide.with(this).load(profile.getPhotoUrl()).circleCrop().into(userImage);

                Timber.d(String.valueOf(profile.getPhotoUrl()));
            }

        });
        HomeActivityPermissionsDispatcher.askPermissionsWithPermissionCheck(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_nav_menu, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeActivityPermissionsDispatcher
                .onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @SuppressLint("MissingPermission")
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION})
    public void askPermissions() {
        viewModel.location(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION})
    public void askRationalePermissions(final PermissionRequest request) {
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION})
    public void askDeniedPermissions() {
        alertDialogPermissionsShow();
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION})
    public void askNeverPermissions() {
        openParamsPhone();
    }

    public void openParamsPhone(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", BuildConfig.APPLICATION_ID, null));
        startActivity(intent);
    }

    public void alertDialogPermissionsShow() {
        new MaterialAlertDialogBuilder(this,
                R.style.AlertDialogStyle)
                .setMessage(R.string.alertDialogPermisssion)
                .setNegativeButton(R.string.alertDialogPermisssionDeny, (dialog, button) ->
                        Timber.d(String.valueOf(R.string.alertDialogPermisssionDeny)))
                .setPositiveButton(R.string.alertDialogPermisssionOk, (dialog, button) ->
                        HomeActivityPermissionsDispatcher
                                .askPermissionsWithPermissionCheck(this))
                .show();
    }
}