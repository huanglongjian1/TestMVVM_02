package com.android.test2mvvm.test5.fragment6.test;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.android.test2mvvm.util.Loge;

public class Test_Worker extends Worker {
    public Test_Worker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inData=getInputData();
        Loge.e(inData.getString("work_key"));
        Data outdata=new Data.Builder().putString("work_key","今晚吃鸡").build();
        return Result.success(outdata);
    }
}
