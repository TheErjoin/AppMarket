package com.example.appmarket.presentation.ui.fragments.apps.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.appmarket.R;
import com.example.appmarket.common.base.BaseFragment;
import com.example.appmarket.common.utils.DownloadUtils;
import com.example.appmarket.databinding.FragmentDetailAppBinding;
import com.example.appmarket.domain.models.AppModel;

public class DetailAppFragment extends BaseFragment<FragmentDetailAppBinding> {

    private DetailAppFragmentArgs args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = DetailAppFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected void setupUI() {
        initView();
    }

    private void initView() {
        binding.textAppName.setText(args.getAppModel().getTitle());
        Glide.with(requireActivity())
                .load(args.getAppModel().getLogo50Link())
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageAppLogo);
    }

    @Override
    protected void setupListeners() {
        clickBackButton();
        clickDownloadButton();
    }

    private void clickDownloadButton() {
        switch (args.getAppModel().getStatus()) {
            case canInstalled:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.canInstalled));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.downloadStart(args.getAppModel(), requireContext()));
                break;
            case downloaded:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.downloaded));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.installApp(args.getAppModel(), requireContext()));
                break;
            case haveUpdated:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.haveUpdated));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.downloadStart(args.getAppModel(), requireContext()));
                break;
            case installed:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.openApp));
                binding.buttonDeleteDetailApp.setVisibility(View.VISIBLE);
                binding.buttonDeleteDetailApp.setOnClickListener(view -> deleteApp(args.getAppModel()));
                binding.buttonInstallDetailApp.setOnClickListener(view -> openApp(args.getAppModel()));
                break;
        }
    }

    private void clickBackButton() {
        binding.buttonArrowBackDetailApp.setOnClickListener(view -> navController.navigateUp());
    }

    private void openApp(AppModel args) {
        Intent launchIntent = requireContext().getPackageManager().getLaunchIntentForPackage(args.getType());
        startActivity(launchIntent);
    }

    private void deleteApp(AppModel appModel) {
        Uri packageURI = Uri.parse("package:" + appModel.getType());
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
    }

    @Override
    protected void setupObservers() {
    }

    @Override
    protected FragmentDetailAppBinding bind() {
        return FragmentDetailAppBinding.inflate(getLayoutInflater());
    }
}