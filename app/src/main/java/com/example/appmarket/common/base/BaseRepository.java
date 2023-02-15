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
    public <T> LiveData<Resource<T>> doRequest(Call<T> call) {
        MutableLiveData<Resource<T>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(Resource.success(response.body()));
                } else {
                    liveData.postValue(Resource.error("Response not successful", null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                liveData.postValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    /**
     * Reusable method that can be used in the future,only without resource file
     */
    public <T> LiveData<T> doRequestWithoutResource(Call<T> call) {
        MutableLiveData<T> liveData = new MutableLiveData<>();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                liveData.postValue(null);
            }
        });
        return liveData;
    }
}
