package com.example.appmarket.presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmarket.databinding.ItemAppBinding;
import com.example.appmarket.presentation.ui.utils.AppsDiffUtilCallback;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppsViewHolder> {

    private final ArrayList<String> apps = new ArrayList<>();

    private final OnClickItem click;

    public AppsAdapter(OnClickItem click) {
        this.click = click;
    }

    @NonNull
    @Override
    public AppsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppsViewHolder(
                ItemAppBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AppsViewHolder holder, int position) {
        holder.onBind(apps.get(position));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public void setApps(List<String> newApps) {
        final AppsDiffUtilCallback diffCallback = new AppsDiffUtilCallback(apps, newApps);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        apps.clear();
        apps.addAll(newApps);
        diffResult.dispatchUpdatesTo(this);
    }

    public class AppsViewHolder extends RecyclerView.ViewHolder {

        private final ItemAppBinding binding;

        public AppsViewHolder(ItemAppBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(view -> click.onClickItem(apps.get(getLayoutPosition())));
        }

        public void onBind(String s) {
            String position = String.valueOf(getLayoutPosition() + 1);
            binding.textAppName.setText(s);
            binding.textCountApp.setText(position);
        }
    }

    public interface OnClickItem {
        void onClickItem(String s);
    }
}
