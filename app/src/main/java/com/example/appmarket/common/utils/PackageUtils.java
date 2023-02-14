package com.example.appmarket.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.appmarket.domain.models.AppModel;

import java.util.Objects;

public class PackageUtils {

    public static boolean hasAppInstalled(String path, Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(path, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    public static boolean hasAppDownloaded(String path, Context context) {
        final PackageManager packageManager = context.getPackageManager();
        String fullPath = "/storage/sdcard/Download/" + path + ".apk";
        PackageInfo info = packageManager.getPackageArchiveInfo(fullPath, 0);
        return info != null;
    }

    public static boolean hasAppUpdated(AppModel appModel, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(appModel.getType(), PackageManager.GET_ACTIVITIES);
            if (!Objects.equals(info.versionName, appModel.getVersion())) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
}
