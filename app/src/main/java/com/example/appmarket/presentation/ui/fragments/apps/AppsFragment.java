package com.example.appmarket.presentation.ui.fragments.apps;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmarket.common.base.BaseFragment;
import com.example.appmarket.common.utils.PackageUtils;
import com.example.appmarket.common.utils.Status;
import com.example.appmarket.databinding.FragmentAppsBinding;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.presentation.ui.adapters.AppsAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AppsFragment extends BaseFragment<FragmentAppsBinding> implements AppsAdapter.OnClickItem {

    private AppsAdapter adapter;
    private AppsViewModel viewModel;

    private final ArrayList<AppModel> list = new ArrayList<>();

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
            switch (listResource.statusNetwork) {
                case SUCCESS:
                    for (AppModel app : listResource.data) {
                        list.addAll(fetchStatus(app));
                    }
                    adapter.setApps(list);
                    binding.progressApps.setVisibility(View.GONE);
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), listResource.msg, Toast.LENGTH_SHORT).show();
                    binding.progressApps.setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.progressApps.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    private ArrayList<AppModel> fetchStatus(AppModel model) {
        ArrayList<AppModel> list = new ArrayList<>();
        if (PackageUtils.hasAppUpdated(model, requireContext())) {
            model.setStatus(Status.haveUpdated);
            list.add(model);
        } else if (PackageUtils.hasAppInstalled(model.getType(), requireContext())) {
            model.setStatus(Status.installed);
            list.add(model);
        } else if (PackageUtils.hasAppDownloaded(model.getType(), requireContext())) {
            model.setStatus(Status.downloaded);
        } else {
            model.setStatus(Status.canInstalled);
            list.add(model);
        }
        return list;
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