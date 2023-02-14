package com.example.appmarket.common.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.example.appmarket.R;
import com.example.appmarket.domain.models.AppModel;

import java.io.File;

public class DownloadUtils {

    public static void downloadStart(AppModel appModel, Context context) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(appModel.getLink()));
        request.setTitle(appModel.getTitle() + ".apk");
        request.setDescription(context.getString(R.string.downloading));
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, appModel.getType() + ".apk");

        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    public static void installApp(AppModel appModel, Context context) {
        File toInstall = new File("/storage/sdcard/Download/", appModel.getType() + ".apk");
        Uri apkUri = Uri.fromFile(toInstall);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openApp(AppModel appModel, Context context) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appModel.getType());
        context.startActivity(launchIntent);
    }

    public static void deleteApp(AppModel appModel, Context context) {
        Uri packageURI = Uri.parse("package:" + appModel.getType());
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

}
