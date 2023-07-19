package com.android.test2mvvm.test6.fragments.newinstance.test07;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

public class WorkFragment extends BackHandledFragment {

    private WebView webView;
    private WebSettings webSettings;

    private String url = "https://www.baidu.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_bottomsheetfragment, container, false);
        webView = (WebView) view.findViewById(R.id.test6_fragment_webview);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);

        //设置webView返回
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Loge.e(keyCode+"===================");
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {//每次返回一级
                        webView.goBack();
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });


        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            Loge.e(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            //     super.onReceivedError(view, errorCode, description, failingUrl);
            //  Toast.makeText(this,"网页加载错误！",0).show();
        }
    }

    @Override
    public boolean onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
            Log.v("webView.goBack()", "webView.goBack()");
            return true;

        } else {
            Log.v("Conversatio退出", "Conversatio退出");
            return false;
        }

    }


}