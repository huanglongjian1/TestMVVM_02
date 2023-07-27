package com.android.test2mvvm.test6.basebottomsheet;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewBottomSheetDialogFragment extends BaseFullBottomSheetFragment {
    @Override
    public void onResume() {
        super.onResume();
        WebView webView = null;
        if (getView() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) getView();
            int viewGroupChildCount = viewGroup.getChildCount();
            for (int i = 0; i < viewGroupChildCount; i++) {
                if (viewGroup.getChildAt(i) instanceof WebView) {
                    webView = (WebView) viewGroup.getChildAt(i);
                }
            }
            if (webView != null) {
                WebView finalWebView = webView;
                webView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int y = finalWebView.getScrollY();
                        if (y > 0) {
                            finalWebView.requestDisallowInterceptTouchEvent(true);
                        }
                        return false;
                    }
                });
                finalWebView.setFocusableInTouchMode(true);
                finalWebView.requestFocus();
                finalWebView.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (finalWebView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
                            finalWebView.goBack();
                            return true;
                        }
                        return false;
                    }
                });
            }
        }


    }
}
