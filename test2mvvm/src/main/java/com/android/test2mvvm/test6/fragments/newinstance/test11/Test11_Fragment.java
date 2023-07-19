package com.android.test2mvvm.test6.fragments.newinstance.test11;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.util.Loge;

public class Test11_Fragment extends BaseFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        WebSettings webSettings=binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/demo.html");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient());
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Loge.e(message+":"+defaultValue);
                result.confirm("返回消息");
                return true;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Loge.e(message);
                result.confirm();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Loge.e(message);
                result.confirm();
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }
}
