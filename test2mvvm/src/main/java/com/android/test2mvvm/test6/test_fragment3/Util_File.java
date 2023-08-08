package com.android.test2mvvm.test6.test_fragment3;

import static android.os.Environment.MEDIA_MOUNTED;

import android.content.Context;
import android.os.Environment;

import com.android.test2mvvm.util.Loge;

import java.io.File;

public class Util_File {
    /**
     * @param context 上下文对象
     * @param dir     存储目录
     * @return
     */
    public static String getFilePath(Context context, String dir) {
        String directoryPath = "";
//判断SD卡是否可用
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            directoryPath = context.getExternalFilesDir(dir).getAbsolutePath();
// directoryPath =context.getExternalCacheDir().getAbsolutePath() ;
        } else {
//没内存卡就存机身内存
            directoryPath = context.getFilesDir() + File.separator + dir;
// directoryPath=context.getCacheDir()+File.separator+dir;
        }
        File file = new File(directoryPath);
        if (!file.exists()) {//判断文件目录是否存在
            file.mkdirs();
        }
        Loge.e("filePath====>" + directoryPath);
        return directoryPath;
    }

}
