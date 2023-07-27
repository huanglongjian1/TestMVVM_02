package com.android.test2mvvm.test6.test_fragment2.four;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ImageLayoutBinding;
import com.android.test2mvvm.test6.test_fragment2.Test_Fragment2;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class F4_Fragment_02 extends Util_BaseFullBottomSheetFragment<ImageLayoutBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.image_layout;
    }

    Uri image_uri;

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        String url = bundle.getString(F4_Fragment_02.class.getSimpleName());
        image_uri = Uri.parse(url);
        Loge.e(url + "-----------");

        binding.test6FragmentImage.setImageURI(image_uri);
        binding.test6FragmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
                dismiss();
            }
        });
    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(F4_Fragment_02.class.getSimpleName(), name);
        Fragment fragment = new F4_Fragment_02();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("F4Fragment_onDestroy");
    }
}
