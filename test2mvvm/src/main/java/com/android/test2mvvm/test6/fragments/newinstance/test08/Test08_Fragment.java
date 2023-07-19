package com.android.test2mvvm.test6.fragments.newinstance.test08;

import android.content.DialogInterface;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;

import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseObserver;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.retrofit2.api.IdeaApiService;
import com.android.test2mvvm.retrofit2.api.RetrofitUtils;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment5.wanan.api.API;
import com.android.test2mvvm.test6.fragments.newinstance.test09.Test09_Fragment;
import com.android.test2mvvm.test6.fragments.paging3.RetrofitClient;
import com.android.test2mvvm.util.Loge;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test08_Fragment extends BaseFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //注入调用接口方法
        binding.test6FragmentWebview.addJavascriptInterface(new WebAppInterface(), "InterfaceName");
        binding.test6FragmentWebview.addJavascriptInterface(new WebAppInterface(), "native");
        binding.test6FragmentWebview.loadUrl("file:///android_asset/index3.html");
        //   binding.test6FragmentWebview.loadUrl("http://www.baidu.com");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                binding.test6FragmentWebview.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }
        });
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:callJava()");

            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();

                Observable<String> observable = RetrofitUtils.getRetrofitBuilder("http://www.baidu.com").build().create(IdeaApiService.class).get_baidu();
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Loge.e(s);
                            }

                            @Override
                            public void onFailure(Throwable e) {

                            }
                        });


                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Loge.e(url + message);
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Confirm");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
                b.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setNeutralButton("选择中间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Loge.e("今晚中间打老虎");
                        Test09_Fragment test09_fragment = new Test09_Fragment();
                        test09_fragment.show(getChildFragmentManager(), Test09_Fragment.class.getSimpleName());
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                if (title != null)
                    binding.test6FragmentBtn.setText(title);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
