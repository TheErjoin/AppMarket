package com.example.appmarket.presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmarket.databinding.ItemAppBinding;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.presentation.ui.utils.AppsDiffUtilCallback;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppsViewHolder> {

    private final ArrayList<AppModel> apps = new ArrayList<>();

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

    public void setApps(List<AppModel> newApps) {
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

        public void onBind(AppModel model) {
            String position = String.valueOf(getLayoutPosition() + 1);
            binding.textAppName.setText(model.getTitle());
            binding.textCountApp.setText(position);
            Glide.with(itemView.getContext()).load(model.getLogo50Link()).into(binding.imageAppLogo);
        }
    }

    public interface OnClickItem {
        void onClickItem(AppModel model);
    }
}
