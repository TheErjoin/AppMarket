package com.example.appmarket.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.appmarket.R;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected VB binding;
    protected abstract VB bind();
    protected NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bind();
        navController = Navigation.findNavController(requireActivity(), R.id.nav_graph);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
        setupObservers();
        setupListeners();
    }

    protected abstract void setupListeners();
    protected abstract void setupObservers();
    protected abstract void setupUI();
}