package com.example.appmarket.presentation.ui.utils;

import androidx.recyclerview.widget.DiffUtil;

import com.example.appmarket.domain.models.AppModel;

import java.util.List;

public class AppsDiffUtilCallback extends DiffUtil.Callback {
    private final List<AppModel> oldData;
    private final List<AppModel> newData;

    public AppsDiffUtilCallback(List<AppModel> oldData, List<AppModel> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }
}