package com.mdasilva.go4lunch.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mdasilva.go4lunch.databinding.MatesListFragmentBinding;
import com.mdasilva.go4lunch.ui.viewModel.MatesListViewModel;

import timber.log.Timber;

public class MatesListFragment extends Fragment {

    private MatesListViewModel viewModel;
    private MatesListFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MatesListViewModel.class);

        binding = MatesListFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}