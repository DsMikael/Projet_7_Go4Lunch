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
import com.mdasilva.go4lunch.ui.viewModel.MatesListFragmentViewModel;
import com.xwray.groupie.GroupieAdapter;

public class MatesListFragment extends Fragment {

    private MatesListFragmentBinding binding;
    private MatesListFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MatesListFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MatesListFragmentViewModel.class);

        GroupieAdapter adapter = new GroupieAdapter();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}