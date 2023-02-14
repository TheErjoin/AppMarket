package com.example.appmarket.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.example.appmarket.domain.models.AppModel;

import java.io.File;
import java.util.Objects;

public class PackageUtils {

    public static boolean hasAppInstalled(String packageName, Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return (packageInfo != null);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean hasAppDownloaded(String path, Context context) {
        final PackageManager packageManager = context.getPackageManager();
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, path + ".apk");
        if (file.exists()) {
            PackageInfo info = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), 0);
            return info != null;
        } else {
            return false;
        }
    }

    public static boolean hasAppUpdated(AppModel appModel, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(appModel.getType(), PackageManager.GET_ACTIVITIES);
            if (!Objects.equals(info.versionName, appModel.getVersion())) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return false;
    }
}
