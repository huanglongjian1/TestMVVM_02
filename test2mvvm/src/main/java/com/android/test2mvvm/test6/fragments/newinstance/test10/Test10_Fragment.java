package com.android.test2mvvm.test6.fragments.newinstance.test10;

import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.newinstance.test08.WebAppInterface;
import com.android.test2mvvm.util.Loge;

public class Test10_Fragment extends BaseFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/index2.html");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient());
        binding.test6FragmentWebview.addJavascriptInterface(new WebAppInterface(), "InterfaceName");
        binding.test6FragmentWebview.addJavascriptInterface(this, "fragment");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.test6FragmentWebview.loadUrl("javascript:callJava()");
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   binding.test6FragmentWebview.loadUrl("javascript:alert('你在哪里呢')");

            }
        });
        String aa = "我在在啊啊啊啊";
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:callJava('" + aa + "')");
            }
        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Loge.e(message + ":" + url);
                result.confirm();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)) {
                    binding.test6FragmentBtn.setText(title);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @JavascriptInterface
    public void test() {
        Loge.e("fragment---test");
    }
}
