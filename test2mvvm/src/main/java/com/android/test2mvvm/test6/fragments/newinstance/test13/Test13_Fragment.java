package com.android.test2mvvm.test6.fragments.newinstance.test13;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment.WebApp;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.IOException;

public class Test13_Fragment extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;
    private static final String APP_CACHE_DIRNAME = "webcache"; // web缓存目录

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
         binding.test6FragmentWebview.loadUrl("http://blog.csdn.net/coder_pig");
     //   binding.test6FragmentWebview.loadUrl("file:///android_asset/index2.html");
        binding.test6FragmentWebview.addJavascriptInterface(new WebApp() {
            @JavascriptInterface
            @Override
            public void test(String string) {
                Loge.e(string + ":测试回调");
            }
        }, "webapp");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                binding.test6FragmentWebview.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.test6FragmentBtn3.setText("开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.test6FragmentBtn3.setText("加载完成");
            }

        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.test6FragmentBtn3.setText("加载进度"+newProgress+"%");
            }
        });
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        // 开启database storage API功能
        webSettings.setDatabaseEnabled(true);
        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + File.separator + APP_CACHE_DIRNAME;
        Log.e("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        File file = new File(cacheDirPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true);
        Log.i("databasepath", webSettings.getDatabasePath());
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.clearCache(true);
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.reload();
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           binding.test6FragmentWebview.loadUrl("javascript:link_test()");
            }
        });
    }
}
