package com.android.test2mvvm.test6.fragments.newinstance.test08;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.android.test2mvvm.util.Loge;

public class WebAppInterface {
    @JavascriptInterface
    public void method1() {
        Log.e("WebAppInterface", "method1");
    }

    @JavascriptInterface
    public void method2(String param1, String param2) {
        Log.e("WebAppInterface", "method2" + param1 + param2);
    }

    @JavascriptInterface
    public void webToNative(String str) {
        Loge.e(str);
    }

    @JavascriptInterface
    public String nativeToWeb() {
        return "msg: native to web";
    }
}
