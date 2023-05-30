package com.android.test2mvvm.test6.fragments.result;

import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventObject {
    public EventObject() {
        EventBus.getDefault().register(this);
    }

    @Subscribe()
    public void onReceiveMsg(String msg) {
        Loge.e(msg+":EventObject");
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }
}
