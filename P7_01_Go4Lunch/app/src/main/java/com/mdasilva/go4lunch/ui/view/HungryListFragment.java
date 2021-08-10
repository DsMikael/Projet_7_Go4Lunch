package com.mdasilva.go4lunch.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mdasilva.go4lunch.databinding.HungryListFragmentBinding;
import com.mdasilva.go4lunch.ui.viewModel.HungryListViewModel;

import timber.log.Timber;

public class HungryListFragment extends Fragment {

    private HungryListViewModel viewModel;
    private HungryListFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HungryListViewModel.class);

        binding = HungryListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Timber.d("FragmentHungry");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}