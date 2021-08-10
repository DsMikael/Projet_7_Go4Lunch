package com.mdasilva.go4lunch.ui.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mdasilva.go4lunch.R;
import com.mdasilva.go4lunch.databinding.ActivityHomeBinding;
import com.mdasilva.go4lunch.ui.viewModel.HomeActivityViewModel;

import timber.log.Timber;

public class HomeActivity extends AppCompatActivity{

    private  ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        HomeActivityViewModel viewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);

        viewModel.getUserMutableLiveData().observe(this, user -> {
            if(user != null) {
                Timber.d("Home Activity User : %s" ,user.getDisplayName());
            }
        });

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
                    .commit();
            return true;
        });

        binding.navView.bringToFront();
        binding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_menu_lunch) {
                Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_menu_settings) {
                Toast.makeText(HomeActivity.this, "The SETTING", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_menu_logout) {
                Toast.makeText(HomeActivity.this, "LOGOUT", Toast.LENGTH_SHORT).show();
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_nav_menu, menu);
        return true;
    }

}