package com.android.test2mvvm.test6.test_fragment.test02;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.util.Loge;

public class Fragment_02 extends BaseFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/demo3.html");
        binding.test6FragmentWebview.addJavascriptInterface(this, "sharp");


        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                binding.test6FragmentWebview.loadUrl("javascript:getGreetings_02()");
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:getGreetings_03()");
            }
        });
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.test6FragmentWebview.loadUrl("javascript:" + "show_01" + "(\" " + "pa--------ram" + "\")");
            }
        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Loge.e(message);
                result.confirm();
                return true;
            }
        });
    }

    @JavascriptInterface
    public void getResult(String result) {
        Loge.e(result);
    }

    @Override
    protected void initData() {

    }
}
