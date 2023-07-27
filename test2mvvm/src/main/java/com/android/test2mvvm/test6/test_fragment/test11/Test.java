package com.android.test2mvvm.test6.test_fragment.test11;

import android.content.Intent;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Test {
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Msg(String msg){
        Loge.e(msg+"------------------------------------");



    }
}
