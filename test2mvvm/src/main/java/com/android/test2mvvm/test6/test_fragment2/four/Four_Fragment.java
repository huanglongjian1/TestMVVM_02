package com.android.test2mvvm.test6.test_fragment2.four;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment.MyWebChromeClient;
import com.android.test2mvvm.test6.test_fragment.MyWebViewClient;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import dagger.hilt.android.components.ActivityComponent;

public class Four_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        binding.test6FragmentWebview.loadUrl("file:///android_asset/demo.html");
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient() {

        });
        binding.test6FragmentWebview.setWebChromeClient(new MyWebChromeClient(getContext()));

    }

    @Override
    protected void initData() {
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
                Loge.e("请开启相应权限");
            }
            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 10001);
        } else {
            Loge.e("已经拥有权限了");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==10001){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Loge.e("授权成功");
            }else {
                Loge.e("授权被拒绝");

            }
        }
    }

    @Override
    protected void onDataLazyLoad() {

    }
}
