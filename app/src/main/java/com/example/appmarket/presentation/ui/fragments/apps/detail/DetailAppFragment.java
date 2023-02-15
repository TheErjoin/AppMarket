package com.example.appmarket.presentation.ui.fragments.apps.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.appmarket.R;
import com.example.appmarket.common.base.BaseFragment;
import com.example.appmarket.common.utils.DownloadUtils;
import com.example.appmarket.databinding.FragmentDetailAppBinding;

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
            case CAN_INSTALLED:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.canInstalled));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.downloadStart(args.getAppModel(), requireContext()));
                break;
            case DOWNLOADED:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.downloaded));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.installApp(args.getAppModel(), requireContext()));
                break;
            case HAVE_UPDATED:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.haveUpdated));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.downloadStart(args.getAppModel(), requireContext()));
                break;
            case INSTALLED:
                binding.buttonInstallDetailApp.setText(requireContext().getString(R.string.openApp));
                binding.buttonDeleteDetailApp.setVisibility(View.VISIBLE);
                binding.buttonDeleteDetailApp.setOnClickListener(view -> DownloadUtils.deleteApp(args.getAppModel(), requireContext()));
                binding.buttonInstallDetailApp.setOnClickListener(view -> DownloadUtils.openApp(args.getAppModel(), requireContext()));
                break;
        }
    }

    private void clickBackButton() {
        binding.buttonArrowBackDetailApp.setOnClickListener(view -> navController.navigateUp());
    }

    @Override
    protected void setupObservers() {
    }

    @Override
    protected FragmentDetailAppBinding bind() {
        return FragmentDetailAppBinding.inflate(getLayoutInflater());
    }
}