package com.android.test2mvvm.test6.test_fragment.test01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.fragments.newinstance.test08.WebAppInterface;
import com.android.test2mvvm.test6.test_fragment.WebApp;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Fragment_01 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.test_bottomsheetfragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/index2.html");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                binding.test6FragmentWebview.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }
        });
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // binding.test6FragmentWebview.loadUrl("javascript:show("+"AAAAA"+")");
                binding.test6FragmentWebview.evaluateJavascript("getGreetings04()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Loge.e(value);
                    }
                });
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:test()");
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:link_test()");
            }
        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                Loge.e(message);
                return true;
            }
        });
        binding.test6FragmentWebview.addJavascriptInterface(new WebAppInterface(), "InterfaceName");
        binding.test6FragmentWebview.addJavascriptInterface(this, "fragment");
        binding.test6FragmentWebview.addJavascriptInterface(new WebApp() {
            @JavascriptInterface
            @Override
            public void test(String string) {
                Loge.e(string);
            }
        }, "webapp");
    }

    @JavascriptInterface
    public void test() {
        Loge.e("回调");
    }

    @Override
    public void onResume() {
        super.onResume();

        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) getDialog();
     //   bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.getWindow().setDimAmount(0F);
        //实现Dialog区域外部事件可以传给Activity

//FLAG_NOT_TOUCH_MODAL作用：即使该window可获得焦点情况下，仍把该window之外的任何event发送到该window之后的其他window
        bottomSheetDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

//FLAG_WATCH_OUTSIDE_TOUCH作用：如果点击事件发生在window之外，就会收到一个特殊的MotionEvent，为ACTION_OUTSIDE
        bottomSheetDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        bottomSheetDialog.getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                  getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                return true;
            }
        });

        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("折叠");
                getBehavior().setPeekHeight(0);
                getBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("fragment_01=====onDestroy");
    }

}
