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
import com.mdasilva.go4lunch.ui.viewModel.HomeActivityViewModel;
import com.xwray.groupie.GroupieAdapter;

import timber.log.Timber;


public class HungryListFragment extends Fragment {

    private HungryListFragmentBinding binding;
    private HomeActivityViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HungryListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);

        GroupieAdapter adapter = new GroupieAdapter();
        binding.listHungry.setAdapter(adapter);

        Timber.d("FragmentHungry");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}