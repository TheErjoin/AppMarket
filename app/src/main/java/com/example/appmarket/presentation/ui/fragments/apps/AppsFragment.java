package com.example.appmarket.presentation.ui.fragments.apps;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appmarket.R;
import com.example.appmarket.databinding.FragmentAppsBinding;
import com.example.appmarket.presentation.ui.adapters.AppsAdapter;

import java.util.ArrayList;

public class AppsFragment extends Fragment implements AppsAdapter.OnClickItem {

    private AppsAdapter adapter;

    private FragmentAppsBinding binding;
    private ArrayList<String> apps = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAppsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        listAdded();
    }

    private void listAdded() {
        apps.add("AppMarket");
        apps.add("AppMarket");
        apps.add("AppMarket");
        apps.add("AppMarket");
        apps.add("AppMarket");
        apps.add("AppMarket");
        apps.add("AppMarket");
        adapter.setApps(apps);
    }

    private void initRecycler() {
        adapter = new AppsAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerApps.setLayoutManager(layoutManager);
        binding.recyclerApps.setAdapter(adapter);
    }

    @Override
    public void onClickItem(String s) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
    }
}