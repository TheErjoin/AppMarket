package com.example.appmarket.presentation.ui.fragments.apps;

import android.Manifest;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmarket.common.base.BaseFragment;
import com.example.appmarket.common.utils.PackageUtils;
import com.example.appmarket.common.utils.Status;
import com.example.appmarket.databinding.FragmentAppsBinding;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.presentation.ui.adapters.AppsAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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
        requestMemoryPermission();
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
        });
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

    private void requestMemoryPermission() {
        ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText(requireContext(), "Memory permission cannot be requested again",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Memory permission denied", Toast.LENGTH_SHORT).show();
                    }
                });
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onClickItem(AppModel model) {
        navigateSafely(AppsFragmentDirections.Companion.actionAppsFragmentToDetailAppFragment(model));
    }
}