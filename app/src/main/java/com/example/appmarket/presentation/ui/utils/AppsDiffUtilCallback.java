package com.example.appmarket.presentation.ui.utils;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class AppsDiffUtilCallback extends DiffUtil.Callback {
    private final List<String> oldData;
    private final List<String> newData;

    public AppsDiffUtilCallback(List<String> oldData, List<String> newData) {
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