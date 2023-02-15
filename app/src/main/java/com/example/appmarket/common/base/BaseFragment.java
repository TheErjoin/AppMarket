package com.example.appmarket.common.base;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.appmarket.R;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected VB binding;

    protected abstract VB bind();

    protected NavController navController;
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bind();
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                setupUI();
                setupObservers();
                setupListeners();
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(requireContext(), requireContext().getString(R.string.permission_denied_always), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), requireContext().getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navigateSafely(NavDirections directions) {
        NavDestination currentDestination = navController.getCurrentDestination();
        assert currentDestination != null;
        NavAction action = currentDestination.getAction(directions.getActionId());
        if (action != null) {
            navController.navigate(directions);
        }
    }

    protected abstract void setupListeners();

    protected abstract void setupObservers();

    protected abstract void setupUI();
}