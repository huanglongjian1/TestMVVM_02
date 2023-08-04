package com.android.test2mvvm.test6.test_fragment;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.test2mvvm.util.Loge;

public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Loge.e(String.valueOf(request.getUrl()));
        if (request.getUrl().toString().startsWith("test")) {
            Intent intent=new Intent("com.action");
            intent.addCategory("android.intent.category.LAUNCHER");
            Uri uri=Uri.parse(request.getUrl().toString());
            intent.setData(uri);
            view.getContext().startActivity(intent);
        }
        return !canOpenWithWebView(String.valueOf(request.getUrl()));
    }

    private boolean canOpenWithWebView(String url) {//处理判断url的合法性
        if (url != null) {
            if (url.startsWith("http:") || url.startsWith("https:")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        handler.proceed();
    }
}
