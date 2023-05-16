package com.android.testmvvm.test2;

import java.util.Random;

public class MVVMModel {

    //模拟网络请求获取数据
    public void getHttpData(String user, MCallback callback){
        Random random = new Random();
        boolean isSuccess = random.nextBoolean();
        if (isSuccess){
            callback.onSuccess("数据获取成功：" + user);
        } else {
            callback.onFailed();
        }
    }
}