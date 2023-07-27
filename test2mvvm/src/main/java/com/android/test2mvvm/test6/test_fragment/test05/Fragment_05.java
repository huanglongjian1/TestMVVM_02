package com.android.test2mvvm.test6.test_fragment.test05;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test5.fragment7.dao.AppDatabase;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment.WebApp;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;

import java.lang.reflect.Field;

public class Fragment_05 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/test1_h5.html");
        //  binding.test6FragmentWebview.loadUrl("http://www.baidu.com");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = String.valueOf(request.getUrl());
                if (url.equals("file:///android_asset/test2.html")) {
                    ARouter.getInstance().build(Constants.TEST5_ACTIVITY).navigation();
                    return true;
                } else {
                    view.loadUrl(url);
                    return false;
                }

            }
        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress < 100) {
                    binding.test6FragmentProgress.setVisibility(View.VISIBLE);
                    binding.test6FragmentProgress.setProgress(newProgress);
                } else {
                    binding.test6FragmentProgress.setVisibility(View.GONE);
                }
            }
        });
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:s()");
            }
        });
        binding.test6FragmentWebview.addJavascriptInterface(new WebApp() {
            @JavascriptInterface
            public String back() {

                return "测试调用back";
            }

            @JavascriptInterface
            @Override
            public void test(String string) {
                Loge.e(string + ":sum方法回调");

                Class<?> clazz = null;
                try {
                    clazz = Class.forName(string);
                    //clazz代表com.example.ext.demo.Person类型
                    //clazz.newInstance()创建的就是Person的对象
                    Field application=clazz.getField("context");
                    application.setAccessible(true);
                    Test2_App test2_app= (Test2_App) application.get(null);

                    AppDatabase database=test2_app.getDb();
                    Loge.e(database.toString()+"反射出来的类");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }, "android");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.evaluateJavascript("sum(5,6)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Loge.e(value);
                    }
                });
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:sum(5,6)");
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        binding.test6FragmentWebview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = binding.test6FragmentWebview.getScrollY();
                if (y > 0) {
                    binding.test6FragmentWebview.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        binding.test6FragmentWebview.setFocusableInTouchMode(true);
        binding.test6FragmentWebview.requestFocus();
        binding.test6FragmentWebview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (binding.test6FragmentWebview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
                    binding.test6FragmentWebview.goBack();
                    return true;
                }
                return false;
            }
        });
    }
}
