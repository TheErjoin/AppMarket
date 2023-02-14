package com.example.appmarket.presentation.ui.fragments.apps;


import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmarket.common.base.BaseFragment;
import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.databinding.FragmentAppsBinding;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.presentation.ui.adapters.AppsAdapter;

import java.util.List;

public class AppsFragment extends BaseFragment<FragmentAppsBinding> implements AppsAdapter.OnClickItem {

    private AppsAdapter adapter;
    private AppsViewModel viewModel;

    @Override
    protected FragmentAppsBinding bind() {
        return FragmentAppsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        initViewModel();
        initRecycler();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(AppsViewModel.class);
    }

    @Override
    protected void setupObservers() {
        observeApps();
    }

    private void observeApps() {
        viewModel.fetchApps();
        viewModel.liveData.observe(this, listResource -> {
            switch (listResource.status) {
                case SUCCESS:
                    adapter.setApps(listResource.data);
                    break;
            }
        });
    }

    @Override
    protected void setupListeners() {
    }

    private void initRecycler() {
        adapter = new AppsAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerApps.setLayoutManager(layoutManager);
        binding.recyclerApps.setAdapter(adapter);
    }

    @Override
    public void onClickItem(AppModel model) {
        Toast.makeText(requireContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
    }
}