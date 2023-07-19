package com.android.test2mvvm.test6.fragments.newinstance.test09;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.fragments.matrix.TouchListener;
import com.android.test2mvvm.util.Loge;

public class Test09_Fragment extends BaseFullBottomSheetFragment {
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
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("点击1");
                binding.test6FragmentWebview.reload();
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("点击2");
            }
        });
        binding.test6FragmentWebview.loadUrl("http://www.baidu.com/");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                binding.test6FragmentWebview.loadUrl(String.valueOf(request.getUrl()));
                Loge.e(String.valueOf(request.getUrl()));
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Loge.e("开始:" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Loge.e("完成:" + url);
            }
        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress < 100) {
                    Loge.e(String.valueOf(newProgress));
                    binding.test6FragmentProgress.setVisibility(View.VISIBLE);
                    binding.test6FragmentProgress.setProgress(newProgress);
                } else {
                    binding.test6FragmentProgress.setVisibility(View.GONE);
                }

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title != null)
                    binding.test6FragmentBtn.setText(title);
            }
        });
    }

    WebView webView;

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        binding.test6FragmentWebview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Loge.e("返回");
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (binding.test6FragmentWebview != null && binding.test6FragmentWebview.canGoBack()) {

                        binding.test6FragmentWebview.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        binding.test6FragmentWebview.setVisibility(View.GONE);

        webView = new WebView(Test2_App.getInstance());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup viewGroup = binding.test6FragmentLinearlayout;
        viewGroup.addView(webView, layoutParams);

        webView.loadUrl("https://www.jd.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //   webView.loadUrl(String.valueOf(request.getUrl()));
                Loge.e(String.valueOf(request.getUrl()));
                return !canOpenWithWebView(String.valueOf(request.getUrl()));

            }
        });

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Loge.e("返回");
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (webView != null && webView.canGoBack()) {

                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title))
                    binding.test6FragmentBtn2.setText(title);
            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                webView.getParent().requestDisallowInterceptTouchEvent(true);
//                int x = (int) event.getRawX();
//                int y = (int) event.getRawY();
//                int lastX = 0;
//                int lastY = 0;
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        lastX = x;
//                        lastY = y;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int deltaY = y - lastY;
//                        int deltaX = x - lastX;
//                        if (Math.abs(deltaX) < Math.abs(deltaY)) {
//                            Loge.e("Y轴滑动");
//                            webView.getParent().requestDisallowInterceptTouchEvent(true);
//                        } else {
//                            Loge.e("X轴滑动");
//                            webView.getParent().requestDisallowInterceptTouchEvent(false);
//                        }
//                    default:
//                        break;
//            }

                return false;
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("btn3");
                webView.loadUrl("javascript:alert('hello')");
            }
        });
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
    public void onDestroy() {
        Loge.e("webdestroy");
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
