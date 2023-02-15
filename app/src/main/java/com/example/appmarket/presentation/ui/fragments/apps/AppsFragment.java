package com.example.appmarket.presentation.ui.fragments.apps;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmarket.common.base.BaseFragment;
import com.example.appmarket.common.utils.PackageUtils;
import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.common.utils.Status;
import com.example.appmarket.databinding.FragmentAppsBinding;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.presentation.ui.adapters.AppsAdapter;
import com.example.appmarket.presentation.ui.service.CheckUpdateService;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AppsFragment extends BaseFragment<FragmentAppsBinding> implements AppsAdapter.OnClickItem {

    private AppsAdapter adapter;
    private AppsViewModel viewModel;

    private Observer<Resource<List<AppModel>>> observer;

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
        observer = listResource -> {
            switch (listResource.statusNetwork) {
                case SUCCESS:
                    ArrayList<AppModel> updatedList = updatedAppList(listResource.data);
                    adapter.setApps(updatedList);
                    binding.progressApps.setVisibility(View.GONE);
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), listResource.msg, Toast.LENGTH_SHORT).show();
                    binding.progressApps.setVisibility(View.GONE);
                    break;
                case LOADING:
                    if (adapter.getItemCount() == 0) {
                        binding.progressApps.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        };
        viewModel.liveData.observe(this, observer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.liveData.removeObserver(observer);
    }

    private ArrayList<AppModel> updatedAppList(List<AppModel> data) {
        ArrayList<AppModel> updatedList = new ArrayList<>();
        for (AppModel app : data) {
            List<AppModel> fetchedList = fetchStatus(app);
            updatedList.addAll(fetchedList);
        }
        return updatedList;
    }

    private ArrayList<AppModel> fetchStatus(AppModel model) {
        ArrayList<AppModel> list = new ArrayList<>();
        if (PackageUtils.isAppUpdated(model, requireContext())) {
            model.setStatus(Status.HAVE_UPDATED);
            list.add(model);
        } else if (PackageUtils.isAppInstalled(model.getType(), requireContext())) {
            model.setStatus(Status.INSTALLED);
            list.add(model);
            viewModel.fetchInstalledApp(model);
        } else if (PackageUtils.isAppDownloaded(model.getType(), requireContext())) {
            model.setStatus(Status.DOWNLOADED);
            list.add(model);
        } else {
            model.setStatus(Status.CAN_INSTALLED);
            list.add(model);
        }
        serviceLaunch();
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

    private void serviceLaunch() {
        Intent service = new Intent(requireContext(), CheckUpdateService.class);
        requireContext().startService(service);
    }
    @Override
    public void onClickItem(AppModel model) {
        navigateSafely(AppsFragmentDirections.Companion.actionAppsFragmentToDetailAppFragment(model));
    }
}