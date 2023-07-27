package com.android.test2mvvm.test6.test_fragment.test03;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class Fragment03 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater);
        Loge.e(getParentFragment().toString() + "=============");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("http://www.baidu.com");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
               view.loadUrl(String.valueOf(request.getUrl()));
                Loge.e(String.valueOf(request.getUrl()));
                if (!String.valueOf(request.getUrl()).startsWith("http")) {
                    view.stopLoading();
                }
                Loge.e(view.toString()+":"+binding.test6FragmentWebview.toString());
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        binding.test6FragmentWebview.setDownloadListener(new MyWebViewDownLoadListener(getContext()));
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.test6FragmentBtn4.setText("加载进度" + newProgress + "%");
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                binding.test6FragmentBtn4.setText(title);
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.test6FragmentWebview.canGoBack()) {
                    binding.test6FragmentWebview.goBack();
                } else {
                    getDialog().dismiss();
                }
            }
        });
        binding.test6FragmentWebview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = binding.test6FragmentWebview.getScrollY();
                // Loge.e(String.valueOf(y));
                if (y > 0) {
                    binding.test6FragmentWebview.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }
}
