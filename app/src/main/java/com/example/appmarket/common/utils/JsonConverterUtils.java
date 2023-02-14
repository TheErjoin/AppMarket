package com.example.appmarket.common.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonConverterUtils {

    /**
     * used for mock data
     */
    public static <T> T fromJson(String file) {
        return new Gson().fromJson(file, new TypeToken<T>() {}.getType());
    }

    public static String jsonFromAssets(Context context, String file) {
        try {
            InputStream is = context.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
