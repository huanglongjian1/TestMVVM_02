package com.android.test2mvvm.test6.test_fragment2.senven;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import com.android.test2mvvm.util.Loge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files_Util {
    public static void readFile(String fileName) {
        File file = new File(getSDCardPath()+fileName);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String string = null;
            StringBuffer sb = new StringBuffer();
            while ((string = br.readLine()) != null) {
                sb.append(string);
            }
            Loge.e(sb.toString());
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createFile(Context context, String fileName) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//                context.startActivity(intent);
                //request for the permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                return;
            }
        }

        File file = new File(getSDCardPath() + fileName);
        if (fileName.indexOf(".") != -1) {
            // 说明包含，即创建文件, 返回值为-1就说明不包含.,即是文件
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("昨夜所有故事，已成了过往");
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Loge.e("创建了文件");
        } else {
            // 创建文件夹
            file.mkdir();
            Loge.e("创建了文件夹");
        }

        Loge.e(file.getAbsolutePath());
    }

    public static String getSDCardPath() {
        String SDPATH = Environment.getExternalStorageDirectory() + File.separator;
        return SDPATH;
    }

}
