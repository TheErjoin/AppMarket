package com.example.appmarket.common.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<R> {

    @NonNull
    public final StatusNetwork statusNetwork;
    public final R data;
    public final String msg;

    public Resource(@NonNull StatusNetwork statusNetwork, R data, String msg) {
        this.statusNetwork = statusNetwork;
        this.data = data;
        this.msg = msg;
    }

    public static <R> Resource<R> success(R data) {
        return new Resource<>(StatusNetwork.SUCCESS, data, null);
    }

    public static <R> Resource<R> loading() {
        return new Resource<>(StatusNetwork.LOADING, null, null);
    }

    public static <R> Resource<R> error(String msg, @Nullable R data) {
        return new Resource<>(StatusNetwork.ERROR, data, msg);
    }


    public enum StatusNetwork {
        SUCCESS,
        LOADING,
        ERROR
    }
}
