package com.android.test2mvvm.test6.test_fragment.test04;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.test_fragment.WebApp;
import com.android.test2mvvm.test6.test_fragment.test01.Fragment_01;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class Test04_Fragment extends BaseFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/index2.html");
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient());
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient());
        binding.test6FragmentWebview.addJavascriptInterface(new WebApp() {
            @JavascriptInterface
            @Override
            public void test(String string) {
                Loge.e("点击生效："+getChildFragmentManager().toString());
                Fragment_01 fragment_01 = (Fragment_01) getChildFragmentManager().findFragmentByTag(Fragment_01.class.getSimpleName());
                if (fragment_01 != null) {
                    Loge.e("复用：" + fragment_01.toString());
                    if (fragment_01.isAdded()){
                       fragment_01.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                    }else {
                        fragment_01.show(getChildFragmentManager(), Fragment_01.class.getSimpleName());
                    }
                } else {
                    fragment_01 = new Fragment_01();
                    Loge.e("新建fragment_01:" + fragment_01.toString() + "::" + getChildFragmentManager().toString());
                    fragment_01.setCancelable(false);
                    fragment_01.show(getChildFragmentManager(), Fragment_01.class.getSimpleName());

                }

            }
        }, "webapp");
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
    }
}
