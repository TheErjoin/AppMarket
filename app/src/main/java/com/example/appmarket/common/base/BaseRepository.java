package com.example.appmarket.common.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmarket.common.utils.Resource;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRepository {

    /**
     * Reusable method that can be used in the future
     */
    public <T> LiveData<Resource<T>> getData(Call<T> call) {
        MutableLiveData<Resource<T>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error("Response not successful", null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}
