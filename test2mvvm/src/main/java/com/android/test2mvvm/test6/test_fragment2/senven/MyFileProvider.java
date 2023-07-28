package com.android.test2mvvm.test6.test_fragment2.senven;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import androidx.core.content.FileProvider;

import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileProvider extends FileProvider {
    public static Uri getUri(Context context) {
        File imagePath = new File(context.getFilesDir(), "images");

        File newFile = new File(String.valueOf(imagePath) + File.separator + "default_image.txt");
        if (!imagePath.exists()) {
            imagePath.mkdirs();
            Loge.e(imagePath.getAbsolutePath());
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(newFile);
            fw.write("过了扬州就没船搭");
            fw.close();
            Loge.e("写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri contentUri = getUriForFile(context, "com.android.test2mvvm.fileprovider", newFile);
        Loge.e(contentUri.toString());
        return contentUri;
    }
}

