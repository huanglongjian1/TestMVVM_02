package com.android.test2mvvm.test6.fragments.test_fragment.bottomsheet01;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.SeekbarFragmentBinding;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.Test6_Activity;
import com.android.test2mvvm.test6.basebottomsheet.test.BaseFull_Test;
import com.android.test2mvvm.util.Loge;

public class MyBottomSheet_Fragment01 extends BaseFull_Test {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_bottomsheetfragment, null, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Test6_Activity test6_activity = (Test6_Activity) context;
        getLifecycle().addObserver(test6_activity.new FragmentLifecycle_02());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebView webView = view.findViewById(R.id.test6_fragment_webview);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);

        //支持缩放

        settings.setUseWideViewPort(true);//设定支持viewport

        settings.setLoadWithOverviewMode(true);

        settings.setBuiltInZoomControls(true);

        settings.setSupportZoom(true);//设定支持缩放

        //打开的网址

        webView.loadUrl("http://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Loge.e(String.valueOf(request.getUrl()));
                //     view.loadUrl(String.valueOf(request.getUrl()));

                Uri uri = Uri.parse(String.valueOf(request.getUrl()));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
